package com.edu.nyiltnappbackend.helper;

import com.edu.nyiltnappbackend.model.UserBE;
import com.edu.nyiltnappbackend.repository.*;
import com.edu.nyiltnappbackend.security.TokenGenerator;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Profile("test")
public class LocalDataSetup implements ApplicationRunner {
    private static final int NR_GEN_USER = 100;

    private final IUserRepository userRepository;
    private final IRegistrationRepository registrationRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LocalDataSetup(IUserRepository userRepository,
                          IRegistrationRepository registrationRepository,
                          PasswordEncoder passwordEncoder
                          ) {
        this.userRepository = userRepository;
        this.registrationRepository = registrationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        Faker faker = new Faker(new Locale("hu"));

            for (int i = 0; i < NR_GEN_USER; ++i) {
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
//                userRepository.save
                final String username = firstName.toLowerCase(Locale.ROOT) + lastName.toLowerCase(Locale.ROOT);
                System.out.println(UserBE.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .username(username)
                        .mobileNumber(faker.phoneNumber().cellPhone().replace("+36", "+40"))
                        .email(username + "@gmail.com")
                        .password(passwordEncoder.encode(username))
                        .role("USER")
                        .token(null)
                        .build());
            }
    }
}

