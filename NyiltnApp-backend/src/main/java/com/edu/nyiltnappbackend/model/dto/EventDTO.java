package com.edu.nyiltnappbackend.model.dto;

import com.edu.nyiltnappbackend.model.UserBE;
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
    private Long id;

    private String hostUser;

    private Long eventMeta;

    private Timestamp startTime;

    private Timestamp endTime;

    private Integer maxAttendeeNr;

    private String link;

    @ToString.Exclude
    private Set<UserDTO> presentators;

    private LocationDTO location;
}
