package com.edu.nyiltnappbackend.model.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDTO extends RepresentationModel<SchoolDTO> {

    private String countyCode;

    private String schoolName;

    private String shortName;

    private LocationDTO location;
}
