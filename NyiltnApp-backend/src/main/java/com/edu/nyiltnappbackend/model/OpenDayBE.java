package com.edu.nyiltnappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Year;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "open_days")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OpenDayBE {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "open_days_gen")
    @SequenceGenerator(name = "open_days_gen", sequenceName = "open_days_seq", allocationSize = 1)
    @ToString.Exclude
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "year")
    private Year year;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "motto")
    private String motto;

    @Column(name = "end_date")
    private Timestamp endDate;

    @OneToMany
    private Set<EventBE> events;
}
