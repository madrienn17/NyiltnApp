package com.edu.nyiltnappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "locations")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LocationBE {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locations_gen")
    @SequenceGenerator(name = "locations_gen", sequenceName = "locations_seq", allocationSize = 1)
    @ToString.Exclude
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "lat")
    private double latCoord;

    @Column(name = "lng")
    private double lngCoord;

    @Column(name = "classroom")
    private String classroom;

    @Override
    public String toString() {
        return  "City: " + cityName + '\n' +
                "Street: " + streetName + '\n' +
                "Number: " + streetNumber + '\n' +
                "Classroom: " + classroom + '\n'+
                "Coordinates: (" + latCoord + ", " + lngCoord + ')';
    }
}
