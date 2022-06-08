package com.edu.nyiltnappbackend.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDTO {
    private  String token;

    @Size(min = 6)
    private String newPassword;
}
