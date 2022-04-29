package com.edu.nyiltnappbackend.helper;

import com.edu.nyiltnappbackend.model.UserBE;
import com.edu.nyiltnappbackend.model.dto.UserDTO;


public class DTOConverters {

    public static UserBE convertDTOToUserBE(UserDTO userDTO) {
        return UserBE.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .username(userDTO.getUsername())
                .mobileNumber(userDTO.getMobileNumber())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .token(userDTO.getToken())
                .event(userDTO.getEvent())
                .build();
    }

    public static UserDTO convertUserBEToDTO(UserBE userBE) {
        return UserDTO.builder()
                .firstName(userBE.getFirstName())
                .lastName(userBE.getLastName())
                .username(userBE.getUsername())
                .mobileNumber(userBE.getMobileNumber())
                .email(userBE.getEmail())
                .password(userBE.getPassword())
                .role(userBE.getRole())
                .token(userBE.getToken())
                .event(userBE.getEvent())
                .build();
    }
}
