package com.edu.nyiltnappbackend.repository;

import com.edu.nyiltnappbackend.model.UserBE;
import com.edu.nyiltnappbackend.security.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
}
