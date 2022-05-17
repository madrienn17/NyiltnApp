package com.edu.nyiltnappbackend.controller;

import com.edu.nyiltnappbackend.helper.MyResponseEntity;
import com.edu.nyiltnappbackend.model.dto.RegistrationDTO;
import com.edu.nyiltnappbackend.model.dto.UserDTO;
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
        } catch (Exception e) {
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

}
