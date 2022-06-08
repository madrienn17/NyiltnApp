package com.edu.nyiltnappbackend.controller;

import com.edu.nyiltnappbackend.helper.DTOConverters;
import com.edu.nyiltnappbackend.helper.MyResponseEntity;
import com.edu.nyiltnappbackend.helper.ServiceException;
import com.edu.nyiltnappbackend.model.UserBE;
import com.edu.nyiltnappbackend.model.dto.UserDTO;
import com.edu.nyiltnappbackend.security.PasswordDTO;
import com.edu.nyiltnappbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.edu.nyiltnappbackend.helper.MyResponseEntity.buildErrorMessage;
import static com.edu.nyiltnappbackend.helper.MyResponseEntity.buildSuccessMessage;

@CrossOrigin(origins = "*", originPatterns = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public MyResponseEntity<?> register(@RequestBody UserDTO userDto) {
        System.out.println(userDto);
        try {
            return buildSuccessMessage(userService.register(userDto));
        } catch (Exception e) {
            return buildErrorMessage(e.getMessage());
        }
    }

    @PostMapping("/auth/login")
    public MyResponseEntity<?> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        try {
            UserBE user = userService.login(username, password);

            return buildSuccessMessage(DTOConverters.convertUserBEToDTO(user));
        } catch (ServiceException e) {
            return buildErrorMessage(e.getMessage());
        }
    }

    @PutMapping("/auth/logout/{username}")
    public MyResponseEntity<?> logOut(@PathVariable String username) {
        try {
            userService.logOut(username);
            return buildSuccessMessage();
        } catch (ServiceException e) {
            return buildErrorMessage(e.getMessage());
        }
    }

    @GetMapping("/{username}")
    public MyResponseEntity<?> getUserByUsername(@PathVariable String username) {
        try {
            return buildSuccessMessage(userService.getUserByUsername(username));
        } catch (ServiceException e) {
            return buildErrorMessage(e.getMessage());
        }
    }

    @PostMapping("/resetPassword")
    public MyResponseEntity<?> resetPassword(@RequestParam("email") String userEmail) {
        try {
            userService.resetPassword(userEmail);
            return buildSuccessMessage();
        } catch (ServiceException e) {
            return buildErrorMessage(e.getMessage());
        }
    }

    @GetMapping("/changePassword")
    public MyResponseEntity<?> showChangePasswordPage(@RequestParam("token") String token) {
        String response = userService.validatePasswordResetToken(token);
        return response == null? buildSuccessMessage() : buildErrorMessage(response);
    }

    @PostMapping("/savePassword")
    public MyResponseEntity<?> savePassword(@RequestBody PasswordDTO passwordDto) {
        try {
            userService.savePassword(passwordDto);
            return buildSuccessMessage();
        } catch (ServiceException e) {
            return buildErrorMessage(e.getMessage());
        }
    }
}
