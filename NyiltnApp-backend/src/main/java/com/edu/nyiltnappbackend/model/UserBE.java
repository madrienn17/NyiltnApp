package com.edu.nyiltnappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "users")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class UserBE {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_gen")
    @SequenceGenerator(name = "users_gen", sequenceName = "users_seq", allocationSize = 1)
    @ToString.Exclude
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "FirstName is mandatory")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "LastName is mandatory")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Email
    private String email;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @ToString.Exclude
    @Min(6)
    private String password;

    @Column(columnDefinition = "varchar(255) default 'USER' ")
    private String role;

    @ToString.Exclude
    private String token;
}

