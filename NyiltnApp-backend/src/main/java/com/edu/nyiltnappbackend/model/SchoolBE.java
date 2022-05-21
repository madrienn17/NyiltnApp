package com.edu.nyiltnappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "schools")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "SchoolBE.findAllOrderedByNameDescending",
    query = "SELECT s.schoolName FROM SchoolBE s ORDER BY s.schoolName asc")

public class SchoolBE {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schools_gen")
    @SequenceGenerator(name = "schools_gen", sequenceName = "schools_seq", allocationSize = 1)
    @ToString.Exclude
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "county_code")
    private String countyCode;

    @Column(name = "name")
    private String schoolName;

    @Column(name = "short_name")
    private String shortName;
}
