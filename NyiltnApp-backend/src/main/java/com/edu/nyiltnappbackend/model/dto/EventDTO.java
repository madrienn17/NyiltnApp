package com.edu.nyiltnappbackend.model.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Timestamp;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class EventDTO extends RepresentationModel<EventDTO> {

    private UserDTO hostUser;

    private Timestamp startDate;

    private Timestamp endDate;

    @ToString.Exclude
    private Set<UserDTO> guestList;

    @ToString.Exclude
    private Set<LocationDTO> locations;
}
