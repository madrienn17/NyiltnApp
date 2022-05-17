package com.edu.nyiltnappbackend.model.dto;

import com.edu.nyiltnappbackend.model.EventBE;
import com.edu.nyiltnappbackend.model.UserBE;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Timestamp;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class RegistrationDTO extends RepresentationModel<RegistrationDTO> {

    private UserDTO registeredUser;

    private Timestamp registrationDate;

    private String schoolName;

    private EventDTO event;
}