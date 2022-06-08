package com.edu.nyiltnappbackend.service;

import com.edu.nyiltnappbackend.helper.DTOConverters;
import com.edu.nyiltnappbackend.helper.ServiceException;
import com.edu.nyiltnappbackend.mail.EmailServiceImpl;
import com.edu.nyiltnappbackend.model.UserBE;
import com.edu.nyiltnappbackend.model.dto.UserDTO;
import com.edu.nyiltnappbackend.repository.IPasswordTokenRepository;
import com.edu.nyiltnappbackend.repository.IUserRepository;
import com.edu.nyiltnappbackend.security.PasswordDTO;
import com.edu.nyiltnappbackend.security.PasswordResetToken;
import com.edu.nyiltnappbackend.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Resource
    private IUserRepository userRepository;

    @Resource
    private IPasswordTokenRepository passwordTokenRepository;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private TokenGenerator tokenGenerator;

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

        var userToSave = DTOConverters.convertDTOToUserBE(userDto);
        userToSave.setPassword(encoder.encode(userDto.getPassword()));

        UserBE savedUser = userRepository.save(userToSave);

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

        UserBE user = userOptional.get();

        if (encoder.matches(password, user.getPassword())) {
            String token = tokenGenerator.getJWTToken(username, user.getRole());
            user.setToken(token);

            return updateUserToken(user);
        }
       throw new ServiceException("Incorrect password!");
    }

    /**
     * Update token
     *
     * @param user user whose token should be updated
     * @return the userDto
     */
    public UserBE updateUserToken(UserBE user) {
        return userRepository.save(user);
    }

    public UserDTO getUserByUsername(String username) throws ServiceException {
        Optional<UserBE> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new ServiceException("No such user registered!");
        }
        return DTOConverters.convertUserBEToDTO(user.get());
    }

    public void logOut(String username) throws ServiceException {
        Optional<UserBE> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new ServiceException("No such user registered!");
        }

        var user = userOpt.get();
        user.setToken(null);
        updateUserToken(user);
    }

    public void resetPassword(String userEmail) throws ServiceException {
        Optional<UserBE> user = userRepository.findByEmail(userEmail);
        if (user.isEmpty()) {
            throw new ServiceException("User not found!");
        }
        String token = UUID.randomUUID().toString();
        createPasswordResetTokenForUser(user.get(), token);
        emailService.constructResetTokenEmail(token, user.get());
    }

    private void createPasswordResetTokenForUser(UserBE user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    public String validatePasswordResetToken(String token) {
        final Optional<PasswordResetToken> passToken = passwordTokenRepository.findByToken(token);

        return passToken.isEmpty() ? "invalidToken"
                : isTokenExpired(passToken.get()) ? "expired"
                : null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }

    public void savePassword(PasswordDTO passwordDto) throws ServiceException {
        String result = validatePasswordResetToken(passwordDto.getToken());

        if(result != null) {
            throw new ServiceException("Password reset token not valid");
        }

        Optional<PasswordResetToken> passwordResetToken = passwordTokenRepository.findByToken(passwordDto.getToken());
        if(passwordResetToken.isPresent()) {
            changeUserPassword(passwordResetToken.get().getUser(), passwordDto.getNewPassword());
        } else {
            throw new ServiceException("User invalid!");
        }
    }

    private void changeUserPassword(UserBE user, String newPassword) {
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
    }
}
