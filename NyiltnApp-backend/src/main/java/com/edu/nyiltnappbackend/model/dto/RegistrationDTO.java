package com.edu.nyiltnappbackend.model.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class RegistrationDTO extends RepresentationModel<RegistrationDTO> {

    private UserDTO user;

    private String schoolName;

}
