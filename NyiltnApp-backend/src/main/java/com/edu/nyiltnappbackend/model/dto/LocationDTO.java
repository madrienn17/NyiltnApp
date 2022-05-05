package com.edu.nyiltnappbackend.model.dto;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO extends RepresentationModel<LocationDTO> {
    private String cityName;

    private String streetName;

    private int streetNumber;

    private double latCoord;

    private double lngCoord;
}
