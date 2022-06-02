package com.edu.nyiltnappbackend.service;

import com.edu.nyiltnappbackend.helper.DTOConverters;
import com.edu.nyiltnappbackend.helper.ServiceException;
import com.edu.nyiltnappbackend.mail.EmailServiceImpl;
import com.edu.nyiltnappbackend.model.UserBE;
import com.edu.nyiltnappbackend.model.dto.UserDTO;
import com.edu.nyiltnappbackend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Optional;

@Service
public class UserService {

    @Resource
    private IUserRepository userRepository;

    @Autowired
    private EmailServiceImpl emailService;

    /**
     * Method to register a new user into the database
     *
     * @param userDto user's info
     * @return the saved user
     */
    public UserDTO register(UserDTO userDto) throws ServiceException {
        Optional<UserBE> userFind = userRepository.findByUsername(userDto.getUsername());
        if (userFind.isPresent()) {
            throw new ServiceException("Username already taken!");
        }
        UserBE savedUser = userRepository.save(DTOConverters.convertDTOToUserBE(userDto));

        emailService.sendSimpleMessage(savedUser.getEmail(),"Welcome!", "You've been successfully registered"
        + " to Nyiltnapp application!\nTake a look into our events, and feel free to join!");
        return DTOConverters.convertUserBEToDTO(savedUser);
    }

    /**
     * Method to search and validate the user
     *
     * @param username the username of the user who logs in
     * @param password password for the user
     *
     * @return {@link UserBE} entity
     * @throws ServiceException if the login is not possible
     */
    public UserBE login(String username, String password) throws ServiceException {
        Optional<UserBE> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new ServiceException("Inexistent user!");
        }

        if (userOptional.get().getPassword().equals(password)) {
            return userOptional.get();
        }
       throw new ServiceException("Incorrect password!");
    }

    /**
     * Update token
     *
     * @param user user whose token should be updated
     * @return the userDto
     */
    public UserDTO updateUserToken(UserBE user) {
        return DTOConverters.convertUserBEToDTO(userRepository.save(user));
    }

    public UserDTO getUserByUsername(String username) throws ServiceException {
        Optional<UserBE> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new ServiceException("No such user registered!");
        }
        return DTOConverters.convertUserBEToDTO(user.get());
    }
}
