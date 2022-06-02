package com.edu.nyiltnappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "registrations")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

@NamedQuery(name = "RegistrationBE.isUserRegisteredToEvent",
                query = "SELECT r FROM RegistrationBE r WHERE r.event.id=:eventId and r.registeredUser.username=:username")
@NamedQuery(name = "RegistrationBE.getEventIdsByUsername",
                query = "SELECT r.event.id FROM RegistrationBE r WHERE r.registeredUser.username=:username")
@NamedQuery(name = "RegistrationBE.getUserEmailsByEventId",
                query = "SELECT r.registeredUser.email FROM RegistrationBE r WHERE r.event.id=:eventId")
public class RegistrationBE {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registrations_gen")
    @SequenceGenerator(name = "registrations_gen", sequenceName = "registrations_seq", allocationSize = 1)
    @ToString.Exclude
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private UserBE registeredUser;

    @Column(name = "registration_date")
    private Timestamp registrationDate;

    @ManyToOne
    private SchoolBE school;

    @ManyToOne
    private EventBE event;

}
