package com.edu.nyiltnappbackend.repository;

import com.edu.nyiltnappbackend.model.UserBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserBE, Long> {
    Optional<UserBE> findByUsername(String username);
}
