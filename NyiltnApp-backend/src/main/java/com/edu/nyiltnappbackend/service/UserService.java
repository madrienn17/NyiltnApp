package com.edu.nyiltnappbackend.service;

import com.edu.nyiltnappbackend.helper.DTOConverters;
import com.edu.nyiltnappbackend.helper.MyResponseEntity;
import com.edu.nyiltnappbackend.helper.ServiceException;
import com.edu.nyiltnappbackend.model.UserBE;
import com.edu.nyiltnappbackend.model.dto.UserDTO;
import com.edu.nyiltnappbackend.repository.IUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Optional;

@Service
public class UserService {

    @Resource
    private IUserRepository userRepository;

    /**
     * Method to register a new user into the database
     *
     * @param userDto user's info
     * @return the saved user
     */
    public UserDTO register(UserDTO userDto) {
        UserBE savedUser = userRepository.save(DTOConverters.convertDTOToUserBE(userDto));
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
}
