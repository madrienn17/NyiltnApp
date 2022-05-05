package com.edu.nyiltnappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "registrations")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationBE {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registrations_gen")
    @SequenceGenerator(name = "registrations_gen", sequenceName = "registrations_seq", allocationSize = 1)
    @ToString.Exclude
    @EqualsAndHashCode.Include
    @Column(name = "registration_id")
    private Long registrationId;

    @ManyToOne
    private UserBE registeredUser;

    @Column(name = "registration_date")
    private Timestamp registrationDate;

    @ManyToOne
    private SchoolBE school;

    @OneToMany
    private Set<EventBE> events;

}
