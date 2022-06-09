package com.edu.nyiltnappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

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

    @ManyToOne(cascade = CascadeType.MERGE)
    private EventMetaBE eventMeta;

    @ManyToOne
    private UserBE hostUser;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "max_attendee_nr")
    @Min(0)
    private Integer maxAttendeeNr;

    @Column(name = "registered_nr")
    private Integer registeredNr;

    @Column(name = "link")
    private String link;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private LocationBE location;

    @Override
    public String toString() {
        return  "Name: " + eventMeta.getName() + '\n' +
                "Description: " + eventMeta.getDescription() + '\n'+
                "Host: " + hostUser.getFirstName() + ' ' + hostUser.getLastName() + '\n' +
                "Start time: " + startTime + '\n' +
                "End time: " + endTime + '\n' +
                "Registered/Max: " + registeredNr + '/' + maxAttendeeNr + '\n' +
                "meet link: " + link + '\n' +
                "Location: " + location;
    }
}
