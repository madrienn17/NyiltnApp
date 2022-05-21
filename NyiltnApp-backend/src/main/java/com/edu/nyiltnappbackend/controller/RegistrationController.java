package com.edu.nyiltnappbackend.controller;

import com.edu.nyiltnappbackend.helper.MyResponseEntity;
import com.edu.nyiltnappbackend.helper.ServiceException;
import com.edu.nyiltnappbackend.model.dto.RegistrationDTO;
import com.edu.nyiltnappbackend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/list")
    public MyResponseEntity<?> getAll() {
        return buildSuccessMessage(registrationService.getAll());
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

    @GetMapping("/{username}")
    public MyResponseEntity<?> getAllRegisteredEventIdsForUser(@PathVariable String username) {
        return buildSuccessMessage(registrationService.getAllRegisteredEventIdsForUser(username));
    }

}
