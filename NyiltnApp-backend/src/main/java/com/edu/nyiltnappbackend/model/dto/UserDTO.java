package com.edu.nyiltnappbackend.model.dto;

import com.edu.nyiltnappbackend.model.EventBE;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO extends RepresentationModel<UserDTO> {
    private String firstName;

    private String lastName;

    private String mobileNumber;

    private String email;

    private String username;

    private String password;

    private String role;

    @ToString.Exclude
    private String token;
}

