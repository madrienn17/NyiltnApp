package com.edu.nyiltnappbackend.service;

import com.edu.nyiltnappbackend.helper.DTOConverters;
import com.edu.nyiltnappbackend.helper.ServiceException;
import com.edu.nyiltnappbackend.mail.EmailServiceImpl;
import com.edu.nyiltnappbackend.model.EventBE;
import com.edu.nyiltnappbackend.model.EventMetaBE;
import com.edu.nyiltnappbackend.model.UserBE;
import com.edu.nyiltnappbackend.model.dto.EventDTO;
import com.edu.nyiltnappbackend.model.dto.EventMainDTO;
import com.edu.nyiltnappbackend.repository.IEventMetaRepository;
import com.edu.nyiltnappbackend.repository.IEventRepository;
import com.edu.nyiltnappbackend.repository.IRegistrationRepository;
import com.edu.nyiltnappbackend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Resource
    private IEventRepository eventRepository;

    @Resource
    private IEventMetaRepository eventMetaRepository;

    @Resource
    private IUserRepository userRepository;

    @Resource
    private IRegistrationRepository registrationRepository;

    @Autowired
    private EmailServiceImpl emailService;

    public EventDTO getById(Long id) throws ServiceException {
        Optional<EventBE> eventBE = eventRepository.findEventBEById(id);
        if (eventBE.isEmpty()) {
            throw new ServiceException("No event with this id!");
        }

        return DTOConverters.convertEventBEToDTO(eventBE.get());
    }

    public List<EventMainDTO> getAll() {
        List<EventMainDTO> eventMainDTOS = new ArrayList<>();
        var events = eventMetaRepository.findAll();

        events.forEach(e -> {
            eventMainDTOS.add(EventMainDTO.builder()
                            .id(e.getId())
                            .name(e.getName())
                            .description(e.getDescription())
                            .eventList(DTOConverters.convertEventListToDTO(
                                    eventRepository.findByEventMeta_Id(e.getId())))
                            .build());
        });
        return eventMainDTOS;
    }

    public EventDTO add(EventDTO event) throws ServiceException {
        Optional<UserBE> host = this.userRepository.findByUsername(event.getHostUser());
        Optional<EventMetaBE> meta = this.eventMetaRepository.findById(event.getEventMeta());
        EventBE eventToSave = DTOConverters.convertDTOToEventBE(event);

        eventToSave.setRegisteredNr(0);

        if (host.isEmpty()) {
            throw new ServiceException("User invalid!");
        }
        eventToSave.setHostUser(host.get());

        if (meta.isEmpty()) {
            throw new ServiceException("Event meta invalid!");
        }
        eventToSave.setEventMeta(meta.get());
        return DTOConverters.convertEventBEToDTO(this.eventRepository.save(eventToSave));
    }

    public void delete(Long id) throws ServiceException {
        var eventOpt = this.eventRepository.findEventBEById(id);
        if (eventOpt.isEmpty()) {
            throw new ServiceException("The object does not exist!");
        }

        // if there are users registered, get the registered user's list
        // and email them
        if (eventOpt.get().getRegisteredNr() > 0) {
            var registrationList = registrationRepository.findByEvent_Id(eventOpt.get().getId());
            registrationList.forEach(registration -> {
                emailService.sendSimpleMessage(registration.getRegisteredUser().getEmail(), "Event Deleted",
                        "We are sorry to announce you, that the event you were registered to," +
                                " was deleted by an admin.\n Look around for other interesting events!\n" +
                                "The deleted event is: " + eventOpt.get());
                registrationRepository.delete(registration);
            });
        }

        this.eventRepository.deleteById(id);
    }

    public EventDTO update(Long id, EventDTO event) throws ServiceException {
        EventBE eventToUpdate = this.eventRepository.getById(id);

        Optional<UserBE> host = this.userRepository.findByUsername(event.getHostUser());
        Optional<EventMetaBE> meta = this.eventMetaRepository.findById(event.getEventMeta());

        if (host.isEmpty()) {
            throw new ServiceException("User invalid!");
        }
        eventToUpdate.setHostUser(host.get());

        if (meta.isEmpty()) {
            throw new ServiceException("Event meta invalid!");
        }
        eventToUpdate.setEventMeta(meta.get());

        eventToUpdate.setStartTime(event.getStartTime());
        eventToUpdate.setEndTime(event.getEndTime());
        eventToUpdate.setMaxAttendeeNr(event.getMaxAttendeeNr());
        eventToUpdate.setRegisteredNr(event.getRegisteredNr());
        eventToUpdate.setLink(event.getLink());

        eventToUpdate.getLocation().setStreetNumber(event.getLocation().getStreetNumber());
        eventToUpdate.getLocation().setStreetName(event.getLocation().getStreetName());
        eventToUpdate.getLocation().setLngCoord(event.getLocation().getLngCoord());
        eventToUpdate.getLocation().setLatCoord(event.getLocation().getLatCoord());
        eventToUpdate.getLocation().setClassroom(event.getLocation().getClassroom());
        eventToUpdate.getLocation().setCityName(event.getLocation().getCityName());

        // if there are users registered, get the registered user's list
        // and email them
        if (eventToUpdate.getRegisteredNr() > 0) {
            var emailList = registrationRepository.getUserEmailsByEventId(eventToUpdate.getId());
            emailList.forEach(email -> emailService.sendSimpleMessage(email, "Event Updated",
                    "Dear user,\nThe event you are registered to," +
                            " was updated by an admin.\n" +
                            "The updated event is: " + eventToUpdate));
        }

        return DTOConverters.convertEventBEToDTO(this.eventRepository.save(eventToUpdate));
    }

    public EventMetaBE addEventMeta(EventMetaBE eventMeta) {
        return this.eventMetaRepository.save(eventMeta);
    }

    public EventMetaBE getMetaById(Long id) throws ServiceException {
        var eventMeta = this.eventMetaRepository.findById(id);
        if (eventMeta.isEmpty()) {
            throw new ServiceException("No Meta object with given id!");
        }
        return eventMeta.get();
    }

    public void deleteEventMeta(Long id) throws ServiceException {
        var metaOpt = this.eventMetaRepository.findById(id);
        if (metaOpt.isEmpty()) {
            throw new ServiceException("The object does not exist!");
        }

        // find every event related to this meta
        this.eventRepository.findByEventMeta_Id(id).forEach(event -> {
            try {
                delete(event.getId());
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        });

        this.eventMetaRepository.deleteById(id);
    }

    public EventMetaBE updateEventMeta(Long id, EventMetaBE eventMetaBE) throws ServiceException {
        var eventMeta = this.eventMetaRepository.findById(id);
        if (eventMeta.isEmpty()) {
            throw new ServiceException("No Meta object with given id!");
        }

        EventMetaBE metaToSave = eventMeta.get();

        metaToSave.setDescription(eventMetaBE.getDescription());
        metaToSave.setName(eventMetaBE.getName());

        // find every event related to this meta
        this.eventRepository.findByEventMeta_Id(id).forEach(event -> {
            // if there are users registered, get the registered user's list
            // and email them
            if (event.getRegisteredNr() > 0) {
                var emailList = registrationRepository.getUserEmailsByEventId(event.getId());
                emailList.forEach(email -> {
                    emailService.sendSimpleMessage(email, "Event-meta Updated",
                            "Dear user,\nThe event you are registered to," +
                                    " was updated by an admin.\n" +
                                    "The updated data is: " + metaToSave);
                });
            }
        });

        return this.eventMetaRepository.save(metaToSave);
    }

}
