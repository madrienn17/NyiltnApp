package com.edu.nyiltnappbackend.helper;

import com.edu.nyiltnappbackend.model.*;
import com.edu.nyiltnappbackend.model.dto.EventDTO;
import com.edu.nyiltnappbackend.model.dto.LocationDTO;
import com.edu.nyiltnappbackend.model.dto.SchoolDTO;
import com.edu.nyiltnappbackend.repository.*;
import com.edu.nyiltnappbackend.security.TokenGenerator;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.github.javafaker.Faker;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@Profile("test")
public class LocalDataSetup implements ApplicationRunner {
    private static final int NR_GEN_USER = 100;
    private static final int NR_GEN_EVENT = 60;
    private static final int NR_GEN_REG = 1000;

    private final IUserRepository userRepository;
    private final IRegistrationRepository registrationRepository;
    private final ISchoolRepository schoolRepository;
    private final IEventMetaRepository eventMetaRepository;
    private final IEventRepository eventRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LocalDataSetup(IUserRepository userRepository,
                          ISchoolRepository schoolRepository,
                          IEventMetaRepository eventMetaRepository,
                          IEventRepository eventRepository,
                          IRegistrationRepository registrationRepository,
                          PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.schoolRepository = schoolRepository;
        this.eventMetaRepository = eventMetaRepository;
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        List<UserBE> users = new ArrayList<>();
        List<UserBE> admins = new ArrayList<>();
        List<SchoolBE> schools;
        List<EventMetaBE> eventMetaList;
        List<EventBE> eventList = new ArrayList<>();
        List<RegistrationBE> registrationList = new ArrayList<>();

        // add real users
        admins.add(UserBE.builder()
                .firstName("Moldovan")
                .lastName("Adrienn")
                .username("moldovan.adrienn")
                .mobileNumber("0744207361")
                .email("moldovan.adrienn17@gmail.com")
                .password(passwordEncoder.encode("adrienn"))
                .role("ADMIN")
                .token(null)
                .build());

        admins.add(UserBE.builder()
                .firstName("Antal")
                .lastName("Margit")
                .username("antal.margit")
                .mobileNumber("0755886622")
                .email("antal.margit@ms.sapientia.ro")
                .password(passwordEncoder.encode("antalmargit"))
                .role("ADMIN")
                .token(null)
                .build());

        admins.add(UserBE.builder()
                .firstName("Janosi Rancz")
                .lastName("Katalin Tunde")
                .username("rancz.tunde")
                .mobileNumber("0755112694")
                .email("rancz.tunde@ms.sapientia.ro")
                .password(passwordEncoder.encode("rancztunde"))
                .role("ADMIN")
                .token(null)
                .build());

         admins = userRepository.saveAll(admins);

        Faker faker = new Faker(new Locale("hu"));

        // add fake users
        for (int i = 0; i < NR_GEN_USER; ++i) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            final String username = firstName.toLowerCase(Locale.ROOT) + lastName.toLowerCase(Locale.ROOT);
            users.add(UserBE.builder()
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
        users = userRepository.saveAll(users);

        // add schools from csv file
        File schoolCsv = new File(
                Objects.requireNonNull(getClass().getClassLoader().getResource("schools.csv")).getFile()
        );
        MappingIterator<SchoolDTO> schoolIter = new CsvMapper().readerWithTypedSchemaFor(SchoolDTO.class)
                .readValues(schoolCsv);
        schools = DTOConverters.convertSchoolDTOListToBE(schoolIter.readAll());
        schools = schoolRepository.saveAll(schools);

        // add event-metas from csv file
        File eventMetaCsv = new File(
                Objects.requireNonNull(getClass().getClassLoader().getResource("event-metas.csv")).getFile()
        );
        MappingIterator<EventMetaBE> eventMetaIter = new CsvMapper().readerWithTypedSchemaFor(EventMetaBE.class)
                .readValues(eventMetaCsv);
        eventMetaList = eventMetaIter.readAll();
        eventMetaList = eventMetaRepository.saveAllAndFlush(eventMetaList);

        // add events
        for (int i = 0; i < NR_GEN_EVENT; ++i) {
            eventList.add(
                    EventBE.builder()
                            .startTime(new Timestamp(faker.date().future(365, TimeUnit.DAYS).getTime()))
                            .endTime(new Timestamp(faker.date().future(366, TimeUnit.DAYS).getTime()))
                            .hostUser(getRandomElement(admins))
                            .maxAttendeeNr(faker.number().numberBetween(10, 100))
                            .link("meet.google.com/" + faker.regexify("[a-z]{3}-[a-z]{3}-[a-z]{3}"))
                            .registeredNr(0)
                            .eventMeta(getRandomElement(eventMetaList))
                            .location(LocationBE.builder()
                                    .cityName(faker.address().cityName())
                                    .latCoord(faker.number().randomDouble(2, -100, +100))
                                    .lngCoord(faker.number().randomDouble(2, -100, +100))
                                    .streetName(faker.address().streetName())
                                    .classroom(faker.address().buildingNumber())
                                    .streetNumber(faker.address().streetAddressNumber())
                                    .build())
                            .build()
            );
        }
        eventList = eventRepository.saveAllAndFlush(eventList);

        // add registrations
        for (int i = 0; i < NR_GEN_REG; ++i) {
            var event = getRandomElement(eventList);
            while (event.getRegisteredNr() >= event.getMaxAttendeeNr()) {
                event = getRandomElement(eventList);
            }
            event.setRegisteredNr(event.getRegisteredNr() + 1);
            event = eventRepository.save(event);
            registrationList.add(
                    RegistrationBE.builder()
                            .registeredUser(getRandomElement(users))
                            .registrationDate(Timestamp.from(Instant.now()))
                            .school(getRandomElement(schools))
                            .event(event)
                            .build()
            );
        }
        registrationRepository.saveAll(registrationList);
    }

    /**
     * Generic typed function to retrieve a random element from a list
     *
     * @param list a list containing any type of objects
     * @param <T>  the object type
     * @return an object
     */
    private <T> T getRandomElement(List<T> list) {
        Random randomizer = new Random();
        return list.get(randomizer.nextInt(list.size()));
    }

}

