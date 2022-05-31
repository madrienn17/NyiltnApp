package com.edu.nyiltnappbackend.helper;

import com.edu.nyiltnappbackend.model.*;
import com.edu.nyiltnappbackend.model.dto.*;

import java.util.List;
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
        if (locationDTO == null) {
            return null;
        }
        return LocationBE.builder()
                .id(locationDTO.getId())
                .cityName(locationDTO.getCityName())
                .latCoord(locationDTO.getLatCoord())
                .lngCoord(locationDTO.getLngCoord())
                .streetName(locationDTO.getStreetName())
                .streetNumber(locationDTO.getStreetNumber())
                .build();
    }

    public static LocationDTO convertLocationBEToDTO(LocationBE locationBE) {
        if (locationBE == null) {
            return null;
        }
        return LocationDTO.builder()
                .id(locationBE.getId())
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
                .startTime(eventDTO.getStartTime())
                .endTime(eventDTO.getEndTime())
//                .hostUser(convertDTOToUserBE(eventDTO.getHostUser()))
                .maxAttendeeNr(eventDTO.getMaxAttendeeNr())
                .link(eventDTO.getLink())
                .registeredNr(eventDTO.getRegisteredNr())
                .location(convertDTOToLocationBE(eventDTO.getLocation()))
                .build();
    }

    public static EventDTO convertEventBEToDTO(EventBE eventBE) {
        return EventDTO.builder()
                .id(eventBE.getId())
                .startTime(eventBE.getStartTime())
                .endTime(eventBE.getEndTime())
                .hostUser(eventBE.getHostUser().getUsername())
                .maxAttendeeNr(eventBE.getMaxAttendeeNr())
                .link(eventBE.getLink())
                .eventMeta(eventBE.getEventMeta().getId())
                .registeredNr(eventBE.getRegisteredNr())
                .location(convertLocationBEToDTO(eventBE.getLocation()))
                .build();
    }

    public static List<EventDTO> convertEventListToDTO(List<EventBE> eventBEList) {
        return eventBEList
                .stream().map(DTOConverters::convertEventBEToDTO)
                .collect(Collectors.toList());
    }

    /**
     * School converters
     */
    public static SchoolDTO convertSchoolBEToDTO(SchoolBE schoolBE) {
        return SchoolDTO.builder()
                .countyCode(schoolBE.getCountyCode())
                .schoolName(schoolBE.getSchoolName())
                .shortName(schoolBE.getShortName())
                .build();
    }

    public static SchoolBE convertDTOToSchoolBE(SchoolDTO schoolDTO) {
        return SchoolBE.builder()
                .countyCode(schoolDTO.getCountyCode())
                .schoolName(schoolDTO.getSchoolName())
                .shortName(schoolDTO.getShortName())
                .build();
    }

    /**
     * Registration converters
     */

    public static RegistrationDTO convertRegistrationBEToDTO(RegistrationBE registrationBE) {
       return RegistrationDTO.builder()
               .user(convertUserBEToDTO(registrationBE.getRegisteredUser()))
               .schoolName(convertSchoolBEToDTO(registrationBE.getSchool()).getSchoolName())
               .eventId(registrationBE.getEvent().getId())
               .build();
    }

}
