package com.edu.nyiltnappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.util.TextBuffer;
import lombok.*;
import org.w3c.dom.Text;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
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
    @Column(name = "description")
    private String description;

    @Override
    public String toString() {
        return "Name: " + name + '\n' +
                "Description: " + description;
    }
}
