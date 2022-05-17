package com.edu.nyiltnappbackend.model.dto;

import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SelectItemDTO {
    private String label;

    private String value;
}
