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

    @GetMapping("")
    public MyResponseEntity<?> getAll() {
        return buildSuccessMessage(eventService.getAll());
    }

    @GetMapping("/{id}")
    public MyResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return buildSuccessMessage(eventService.getById(id));
        } catch (ServiceException e) {
            return buildErrorMessage(e.getMessage(), e);
        }
    }

    @PostMapping("")
    public MyResponseEntity<?> add(@RequestBody EventDTO event) {
        try {
            return buildSuccessMessage(eventService.add(event));
        } catch (Exception e) {
            return buildErrorMessage(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public MyResponseEntity<?> delete(@PathVariable Long id) {
        try {
            eventService.delete(id);
            return buildSuccessMessage();
        } catch (ServiceException e) {
            return buildErrorMessage(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public MyResponseEntity<?> update(@PathVariable Long id, @RequestBody EventDTO event) {
        try {
            return buildSuccessMessage(eventService.update(id, event));
        } catch (ServiceException e) {
            return buildErrorMessage(e.getMessage());
        }
    }

    @GetMapping("/meta/{id}")
    public MyResponseEntity<?> getMetaById(@PathVariable Long id) {
        try {
            return buildSuccessMessage(eventService.getMetaById(id));
        } catch (ServiceException e) {
            return buildErrorMessage(e.getMessage(), e);
        }
    }

    @PostMapping("/meta")
    public MyResponseEntity<?> addMeta(@RequestBody EventMetaBE eventMetaBE) {
        return buildSuccessMessage(eventService.addEventMeta(eventMetaBE));
    }
}
