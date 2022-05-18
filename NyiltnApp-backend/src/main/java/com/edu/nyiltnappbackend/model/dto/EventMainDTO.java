package com.edu.nyiltnappbackend.model.dto;

import lombok.*;

import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EventMainDTO {
    private Long id;

    private String name;

    private String description;

    // the event objects which have the event_meta_id = id
    private Set<EventDTO> eventList;
}
