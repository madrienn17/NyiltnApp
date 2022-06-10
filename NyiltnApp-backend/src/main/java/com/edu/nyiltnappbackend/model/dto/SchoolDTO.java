package com.edu.nyiltnappbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "countyCode", "schoolName" })
public class SchoolDTO extends RepresentationModel<SchoolDTO> {

    private String countyCode;

    private String schoolName;
}
