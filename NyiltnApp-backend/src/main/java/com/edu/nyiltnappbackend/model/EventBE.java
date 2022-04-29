package com.edu.nyiltnappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    @Column(name = "event_id")
    private Long eventId;

    @ManyToOne
    private UserBE hostUser;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "guests")
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.ALL})
    private Set<UserBE> guestList;

    @ManyToMany
    private Set<LocationBE> locations;
}
