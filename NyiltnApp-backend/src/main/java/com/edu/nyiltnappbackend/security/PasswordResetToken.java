package com.edu.nyiltnappbackend.security;

import com.edu.nyiltnappbackend.model.UserBE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = UserBE.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserBE user;

    private Date expiryDate;

    public PasswordResetToken(String token, UserBE user) {
        this.token = token;
        this.user = user;
        this.expiryDate = new Date(new Date().getTime() + 86400000);
    }
}