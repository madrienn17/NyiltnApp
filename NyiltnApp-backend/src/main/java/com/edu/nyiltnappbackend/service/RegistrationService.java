package com.edu.nyiltnappbackend.service;

import com.edu.nyiltnappbackend.helper.DTOConverters;
import com.edu.nyiltnappbackend.helper.ServiceException;
import com.edu.nyiltnappbackend.model.RegistrationBE;
import com.edu.nyiltnappbackend.model.SchoolBE;
import com.edu.nyiltnappbackend.model.UserBE;
import com.edu.nyiltnappbackend.model.dto.RegistrationDTO;
import com.edu.nyiltnappbackend.repository.IRegistrationRepository;
import com.edu.nyiltnappbackend.repository.ISchoolRepository;
import com.edu.nyiltnappbackend.repository.IUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    @Resource
    private IRegistrationRepository registrationRepository;

    @Resource
    private IUserRepository userRepository;

    @Resource
    private ISchoolRepository schoolRepository;

    public RegistrationDTO add(RegistrationDTO registrationDTO) throws ServiceException {
        RegistrationBE registrationToSave = new RegistrationBE();

        Optional<UserBE> userBE = userRepository.findByUsername(registrationDTO.getUser().getUsername());
        if (userBE.isEmpty()) {
            throw new ServiceException("No such user registered!");
        }
        UserBE user = userBE.get();

        user.setFirstName(registrationDTO.getUser().getFirstName());
        user.setLastName(registrationDTO.getUser().getLastName());
        user.setMobileNumber(registrationDTO.getUser().getMobileNumber());

        userRepository.save(user);

        registrationToSave.setRegisteredUser(userBE.get());

        registrationToSave.setRegistrationDate(Timestamp.from(Instant.now()));

        Optional<SchoolBE> schoolBE = schoolRepository.getBySchoolName(registrationDTO.getSchoolName());
        if (schoolBE.isEmpty()) {
            throw new ServiceException("No such school!");
        }
        registrationToSave.setSchool(schoolBE.get());

        return  DTOConverters.convertRegistrationBEToDTO(registrationRepository.save(registrationToSave));
    }

    public List<RegistrationBE> getAll() {
        return registrationRepository.findAll();
    }

}
