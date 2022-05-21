package com.edu.nyiltnappbackend.service;

import com.edu.nyiltnappbackend.helper.DTOConverters;
import com.edu.nyiltnappbackend.model.EventMetaBE;
import com.edu.nyiltnappbackend.model.dto.EventDTO;
import com.edu.nyiltnappbackend.model.dto.EventMainDTO;
import com.edu.nyiltnappbackend.repository.IEventMetaRepository;
import com.edu.nyiltnappbackend.repository.IEventRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    @Resource
    private IEventRepository eventRepository;
    @Resource
    private IEventMetaRepository eventMetaRepository;

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

    public EventDTO add(EventDTO event) {
        return DTOConverters.convertEventBEToDTO(
                this.eventRepository.save(DTOConverters.convertDTOToEventBE(event)));
    }

    public EventMetaBE addEventMeta(EventMetaBE eventMeta) {
        return this.eventMetaRepository.save(eventMeta);
    }
}
