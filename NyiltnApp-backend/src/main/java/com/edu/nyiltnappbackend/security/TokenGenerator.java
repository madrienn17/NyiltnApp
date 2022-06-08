package com.edu.nyiltnappbackend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that creates session tokens.
 */
@Component
public class TokenGenerator {
    @Value("${token.expiration.millis}")
    private Integer millisTillInvalidation;

    /**
     * Creates a new token.
     * @param username username for which the token is created
     * @return the token
     */
    public String getJWTToken(String username, String role) {
        String secretKey = "mySecretKey";

        String token = Jwts
                .builder()
                .setId(username)
                .setSubject(role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + millisTillInvalidation))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        return "Bearer " + token;
    }
}
