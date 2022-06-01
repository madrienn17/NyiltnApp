package com.edu.nyiltnappbackend.controller;

import com.edu.nyiltnappbackend.helper.MyResponseEntity;
import com.edu.nyiltnappbackend.helper.ServiceException;
import com.edu.nyiltnappbackend.model.dto.RegistrationDTO;
import com.edu.nyiltnappbackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import static com.edu.nyiltnappbackend.helper.MyResponseEntity.buildErrorMessage;
import static com.edu.nyiltnappbackend.helper.MyResponseEntity.buildSuccessMessage;


@CrossOrigin(origins = "*", originPatterns = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private SchoolService schoolService;

    @PostMapping("")
    public MyResponseEntity<?> add(@RequestBody RegistrationDTO registrationDTO) {
        try {
            return buildSuccessMessage(registrationService.add(registrationDTO));
        } catch (ServiceException e) {
            return buildErrorMessage(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public MyResponseEntity<?> getByEventId(@PathVariable Long id) {
        return buildSuccessMessage(registrationService.getByEventId(id));
    }

    @GetMapping("/schools")
    public MyResponseEntity<?> findSchoolNamesOrderedByNameDescending() {
        return buildSuccessMessage(schoolService.findAllOrderedByNameDescending());
    }

    @GetMapping("/isUserRegistered/{eventId}/{username}")
    public MyResponseEntity<?> isUserRegisteredToEvent(@PathVariable Long eventId,
                                                       @PathVariable String username) {
        return buildSuccessMessage(registrationService.isUserRegisteredToEvent(eventId, username));
    }

    @GetMapping("/registered/{username}")
    public MyResponseEntity<?> getAllRegisteredEventIdsForUser(@PathVariable String username) {
        return buildSuccessMessage(registrationService.getAllRegisteredEventIdsForUser(username));
    }

    @DeleteMapping("/{id}/{username}")
    public MyResponseEntity<?> deleteRegistrationByEventIdAndUsername(@PathVariable String  username,
                                                                      @PathVariable Long id) {
        try {
            registrationService.remove(id, username);
            return buildSuccessMessage();
        } catch (ServiceException e) {
            return buildErrorMessage(e.getMessage());
        }
    }

}
