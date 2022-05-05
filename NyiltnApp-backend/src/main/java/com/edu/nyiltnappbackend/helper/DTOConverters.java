package com.edu.nyiltnappbackend.helper;

import com.edu.nyiltnappbackend.model.*;
import com.edu.nyiltnappbackend.model.dto.*;

import java.util.stream.Collectors;


public class DTOConverters {

    /**
     * User converters
     */
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
                .build();
    }

    /**
     * Location converters
     */
    public static LocationBE convertDTOToLocationBE(LocationDTO locationDTO) {
        return LocationBE.builder()
                .cityName(locationDTO.getCityName())
                .latCoord(locationDTO.getLatCoord())
                .lngCoord(locationDTO.getLngCoord())
                .streetName(locationDTO.getStreetName())
                .streetNumber(locationDTO.getStreetNumber())
                .build();
    }

    public static LocationDTO convertLocationBEToDTO(LocationBE locationBE) {
        return LocationDTO.builder()
                .cityName(locationBE.getCityName())
                .latCoord(locationBE.getLatCoord())
                .lngCoord(locationBE.getLngCoord())
                .streetName(locationBE.getStreetName())
                .streetNumber(locationBE.getStreetNumber())
                .build();
    }

    /**
     * Event converters
     */
    public static EventBE convertDTOToEventBE(EventDTO eventDTO) {
        return EventBE.builder()
                .endDate(eventDTO.getEndDate())
                .startDate(eventDTO.getStartDate())
                .endDate(eventDTO.getEndDate())
                .hostUser(convertDTOToUserBE(eventDTO.getHostUser()))
                .guestList(eventDTO.getGuestList()
                        .stream().map(DTOConverters::convertDTOToUserBE)
                        .collect(Collectors.toSet()))
                .locations(eventDTO.getLocations()
                        .stream().map(DTOConverters::convertDTOToLocationBE)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static EventDTO convertEventBEToDTO(EventBE eventBE) {
        return EventDTO.builder()
                .endDate(eventBE.getEndDate())
                .startDate(eventBE.getStartDate())
                .endDate(eventBE.getEndDate())
                .hostUser(convertUserBEToDTO(eventBE.getHostUser()))
                .guestList(eventBE.getGuestList()
                        .stream().map(DTOConverters::convertUserBEToDTO)
                        .collect(Collectors.toSet()))
                .locations(eventBE.getLocations()
                        .stream().map(DTOConverters::convertLocationBEToDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    /**
     * School converters
     */
    public static SchoolDTO convertSchoolBEToDTO(SchoolBE schoolBE) {
        return SchoolDTO.builder()
                .countyCode(schoolBE.getCountyCode())
                .schoolName(schoolBE.getSchoolName())
                .shortName(schoolBE.getShortName())
                .location(convertLocationBEToDTO(schoolBE.getLocation()))
                .build();
    }

    public static SchoolBE convertDTOToSchoolBE(SchoolDTO schoolDTO) {
        return SchoolBE.builder()
                .countyCode(schoolDTO.getCountyCode())
                .schoolName(schoolDTO.getSchoolName())
                .shortName(schoolDTO.getShortName())
                .location(convertDTOToLocationBE(schoolDTO.getLocation()))
                .build();
    }

    /**
     * Registration converters
     */

    public static RegistrationDTO convertRegistrationBEToDTO(RegistrationBE registrationBE) {
       return RegistrationDTO.builder()
               .user(convertUserBEToDTO(registrationBE.getRegisteredUser()))
               .schoolName(convertSchoolBEToDTO(registrationBE.getSchool()).getSchoolName())
               .build();
    }

}
