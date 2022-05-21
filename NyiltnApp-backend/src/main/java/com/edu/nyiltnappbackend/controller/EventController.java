package com.edu.nyiltnappbackend.controller;

import com.edu.nyiltnappbackend.helper.MyResponseEntity;
import com.edu.nyiltnappbackend.model.EventMetaBE;
import com.edu.nyiltnappbackend.model.dto.EventDTO;
import com.edu.nyiltnappbackend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import static com.edu.nyiltnappbackend.helper.MyResponseEntity.buildErrorMessage;
import static com.edu.nyiltnappbackend.helper.MyResponseEntity.buildSuccessMessage;

@CrossOrigin(origins = "*", originPatterns = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/getAll")
    public MyResponseEntity<?> getAll() {
        return buildSuccessMessage(eventService.getAll());
    }

    @PostMapping("/save")
    public MyResponseEntity<?> add(@RequestBody EventDTO event) {
        try {
            return buildSuccessMessage(eventService.add(event));
        } catch (Exception e) {
            return buildErrorMessage(e.getMessage());
        }
    }

    @PostMapping("/meta/save")
    public MyResponseEntity<?> addMeta(@RequestBody EventMetaBE eventMetaBE) {
        return buildSuccessMessage(eventService.addEventMeta(eventMetaBE));
    }
}
