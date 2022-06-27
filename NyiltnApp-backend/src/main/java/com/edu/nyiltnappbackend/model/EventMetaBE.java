package com.edu.nyiltnappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "event_meta")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "name", "description" })
public class EventMetaBE {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_meta_gen")
    @SequenceGenerator(name = "event_meta_gen", sequenceName = "event_meta_seq", allocationSize = 1)
    @ToString.Exclude
    @EqualsAndHashCode.Include

    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Override
    public String toString() {
        return "Name: " + name + '\n' +
                "Description: " + description;
    }
}
