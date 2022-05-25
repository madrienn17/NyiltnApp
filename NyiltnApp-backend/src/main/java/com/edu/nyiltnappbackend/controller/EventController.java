package com.edu.nyiltnappbackend.controller;

import com.edu.nyiltnappbackend.helper.MyResponseEntity;
import com.edu.nyiltnappbackend.helper.ServiceException;
import com.edu.nyiltnappbackend.model.EventMetaBE;
import com.edu.nyiltnappbackend.model.dto.EventDTO;
import com.edu.nyiltnappbackend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/getById/{id}")
    public MyResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return buildSuccessMessage(eventService.getById(id));
        } catch (ServiceException e) {
            return buildErrorMessage(e.getMessage(), e);
        }
    }

    @GetMapping("/meta/getById/{id}")
    public MyResponseEntity<?> getMetaById(@PathVariable Long id) {
        try {
            return buildSuccessMessage(eventService.getMetaById(id));
        } catch (ServiceException e) {
            return buildErrorMessage(e.getMessage(), e);
        }
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
