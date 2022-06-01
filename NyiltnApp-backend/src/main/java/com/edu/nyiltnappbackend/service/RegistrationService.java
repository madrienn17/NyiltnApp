package com.edu.nyiltnappbackend.service;

import com.edu.nyiltnappbackend.helper.DTOConverters;
import com.edu.nyiltnappbackend.helper.ServiceException;
import com.edu.nyiltnappbackend.model.EventBE;
import com.edu.nyiltnappbackend.model.RegistrationBE;
import com.edu.nyiltnappbackend.model.SchoolBE;
import com.edu.nyiltnappbackend.model.UserBE;
import com.edu.nyiltnappbackend.model.dto.RegistrationDTO;
import com.edu.nyiltnappbackend.repository.IEventRepository;
import com.edu.nyiltnappbackend.repository.IRegistrationRepository;
import com.edu.nyiltnappbackend.repository.ISchoolRepository;
import com.edu.nyiltnappbackend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        return  DTOConverters.convertRegistrationBEToDTO(registrationRepository.save(registrationToSave));
    }

    public void remove(Long eventId, String username) throws ServiceException {
        var registrationOptional =
                registrationRepository.findByEvent_IdAndRegisteredUser_Username(eventId, username);

        if (registrationOptional.isEmpty()) {
            throw new ServiceException("User is not registered to the chosen event!");
        }

        RegistrationBE registration = registrationOptional.get();

        registration.getEvent().setRegisteredNr(registration.getEvent().getRegisteredNr() - 1);

        registrationRepository.delete(registration);
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
}
