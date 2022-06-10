package com.edu.nyiltnappbackend.service;

import com.edu.nyiltnappbackend.helper.DTOConverters;
import com.edu.nyiltnappbackend.helper.ServiceException;
import com.edu.nyiltnappbackend.mail.EmailServiceImpl;
import com.edu.nyiltnappbackend.model.EventBE;
import com.edu.nyiltnappbackend.model.RegistrationBE;
import com.edu.nyiltnappbackend.model.SchoolBE;
import com.edu.nyiltnappbackend.model.UserBE;
import com.edu.nyiltnappbackend.model.dto.RegistrationDTO;
import com.edu.nyiltnappbackend.model.dto.SelectItemDTO;
import com.edu.nyiltnappbackend.repository.IEventRepository;
import com.edu.nyiltnappbackend.repository.IRegistrationRepository;
import com.edu.nyiltnappbackend.repository.ISchoolRepository;
import com.edu.nyiltnappbackend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    @Resource
    private IRegistrationRepository registrationRepository;

    @Resource
    private IUserRepository userRepository;

    @Resource
    private ISchoolRepository schoolRepository;

    @Resource
    private IEventRepository eventRepository;

    @Autowired
    private EmailServiceImpl emailService;

    public RegistrationDTO add(RegistrationDTO registrationDTO) throws ServiceException {
        RegistrationBE registrationToSave = new RegistrationBE();

        Optional<EventBE> eventBE = eventRepository.findEventBEById(registrationDTO.getEventId());
        if (eventBE.isEmpty()) {
            throw new ServiceException("No such event!");
        }

        EventBE event = eventBE.get();

        if (event.getRegisteredNr() >= event.getMaxAttendeeNr()) {
            throw new ServiceException("No more places available!");
        }

        event.setRegisteredNr(event.getRegisteredNr() + 1);

        registrationToSave.setEvent(event);

        Optional<UserBE> userBE = userRepository.findByUsername(registrationDTO.getUser().getUsername());
        if (userBE.isEmpty()) {
            throw new ServiceException("No such user registered!");
        }

        registrationToSave.setRegisteredUser(userBE.get());

        registrationToSave.setRegistrationDate(Timestamp.from(Instant.now()));

        Optional<SchoolBE> schoolBE = schoolRepository.getBySchoolName(registrationDTO.getSchoolName());
        if (schoolBE.isEmpty()) {
            throw new ServiceException("No such school!");
        }
        registrationToSave.setSchool(schoolBE.get());

        if (isUserRegisteredToEvent(eventBE.get().getId(), userBE.get().getUsername())) {
            throw new ServiceException("User Already registered!");
        }

        emailService.sendSimpleMessage(userBE.get().getEmail(), "Event Registration",
                "Hello " + userBE.get().getUsername() + ",\n" +
                        "Thank you for deciding to participate on our " + event.getEventMeta().getName() +
                        " event!\n See the event's details below:\n" + event);

        return DTOConverters.convertRegistrationBEToDTO(registrationRepository.save(registrationToSave));
    }

    public void remove(Long eventId, String username) throws ServiceException {
        var registrationOptional =
                registrationRepository.findByEvent_IdAndRegisteredUser_Username(eventId, username);

        if (registrationOptional.isEmpty()) {
            throw new ServiceException("User is not registered to the chosen event!");
        }

        RegistrationBE registration = registrationOptional.get();

        EventBE event = registration.getEvent();

        event.setRegisteredNr(event.getRegisteredNr() - 1);

        registrationRepository.delete(registration);

        emailService.sendSimpleMessage(registration.getRegisteredUser().getEmail(), "Event Un-Registration",
                "Hello " + registration.getRegisteredUser().getUsername() + ",\n" +
                        "We are sorry to hear that you unregistered from our " + event.getEventMeta().getName() +
                        " event!\n You can still re-join if you make up your mind.\nSee the event's details below:\n" +
                        event);
    }

    public List<RegistrationBE> getByEventId(Long eventId) {
        return registrationRepository.findByEvent_Id(eventId);
    }

    public boolean isUserRegisteredToEvent(Long eventId, String username) {
        Optional<RegistrationBE> registrationBE = registrationRepository.isUserRegisteredToEvent(eventId, username);
        return registrationBE.isPresent();
    }

    public List<Long> getAllRegisteredEventIdsForUser(String username) {
        Optional<List<Long>> eventIds = registrationRepository.getEventIdsByUsername(username);
        return eventIds.orElseGet(ArrayList::new);
    }

    public Set<SelectItemDTO> getRegisteredNrByCounty() {
        Set<SelectItemDTO> registeredByCountyDTOS = new HashSet<>();

        var countyCodes = schoolRepository.findAll()
                .stream()
                .map(SchoolBE::getCountyCode)
                .collect(Collectors.toList());
        countyCodes
                .forEach(county -> {
                    if (!county.isEmpty()) {
                        registeredByCountyDTOS.add(
                                SelectItemDTO
                                        .builder()
                                        .label(String.valueOf(registrationRepository.countBySchool_CountyCode(county)))
                                        .value(county)
                                        .build());
                    }
                });

        return registeredByCountyDTOS;
    }

    public List<SelectItemDTO> getRegisteredBySchool() {
        List<SelectItemDTO> registeredBySchool = new ArrayList<>();

        var schoolNames = schoolRepository.findAll()
                .stream()
                .map(SchoolBE::getSchoolName)
                .collect(Collectors.toList());

        schoolNames
                .forEach(school ->
                        registeredBySchool.add(
                                SelectItemDTO
                                        .builder()
                                        .label(school)
                                        .value(String.valueOf(registrationRepository.countBySchool_SchoolName(school)))
                                        .build()
                        ));

        return registeredBySchool;
    }
}
