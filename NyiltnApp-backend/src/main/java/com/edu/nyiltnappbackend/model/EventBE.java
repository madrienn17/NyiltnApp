package com.edu.nyiltnappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "events")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class EventBE {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_gen")
    @SequenceGenerator(name = "events_gen", sequenceName = "events_seq", allocationSize = 1)
    @ToString.Exclude
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private EventMetaBE eventMeta;

    @ManyToOne
    private UserBE hostUser;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "max_attendee_nr")
    private Integer maxAttendeeNr;

    @Column(name = "link")
    private String link;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private LocationBE location;

    @ManyToMany
    private Set<UserBE> presentators;
}
