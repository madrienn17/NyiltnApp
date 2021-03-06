package com.edu.nyiltnappbackend.repository;

import com.edu.nyiltnappbackend.model.RegistrationBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRegistrationRepository extends JpaRepository<RegistrationBE, Long> {
    Optional<RegistrationBE> isUserRegisteredToEvent(Long eventId, String username);
    Optional<List<Long>> getEventIdsByUsername(String username);
    List<RegistrationBE> findByEvent_Id(Long eventId);
    Optional<RegistrationBE> findByEvent_IdAndRegisteredUser_Username(Long eventId, String username);
    List<String> getUserEmailsByEventId(Long eventId);
    int countBySchool_CountyCode(String countyCode);
    int countBySchool_SchoolName(String schoolName);
}
