package com.edu.nyiltnappbackend.model.dto;

import com.edu.nyiltnappbackend.model.EventBE;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Timestamp;
import java.time.Year;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class OpenDayDTO extends RepresentationModel<OpenDayDTO> {
    private Year year;

    private Timestamp startDate;

    private Timestamp endDate;

    @ToString.Exclude
    private Set<EventDTO> events;
}
