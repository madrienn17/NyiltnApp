package com.edu.nyiltnappbackend.repository;

import com.edu.nyiltnappbackend.model.RegistrationBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRegistrationRepository extends JpaRepository<RegistrationBE, Long> {
    Optional<RegistrationBE> isUserRegisteredToEvent(Long eventId, String username);
}
