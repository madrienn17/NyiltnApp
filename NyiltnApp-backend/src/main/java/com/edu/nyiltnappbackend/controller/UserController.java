package com.edu.nyiltnappbackend.controller;

import com.edu.nyiltnappbackend.helper.MyResponseEntity;
import com.edu.nyiltnappbackend.helper.ServiceException;
import com.edu.nyiltnappbackend.model.UserBE;
import com.edu.nyiltnappbackend.model.dto.UserDTO;
import com.edu.nyiltnappbackend.security.TokenGenerator;
import com.edu.nyiltnappbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.edu.nyiltnappbackend.helper.MyResponseEntity.buildErrorMessage;
import static com.edu.nyiltnappbackend.helper.MyResponseEntity.buildSuccessMessage;

@CrossOrigin(origins = "*", originPatterns = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenGenerator tokenGenerator;

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

            String token = tokenGenerator.getJWTToken(username);
            user.setToken(token);
            UserDTO userDto = userService.updateUserToken(user);
            userDto.setToken(token);

            return buildSuccessMessage(userDto);
        } catch (ServiceException e) {
            return buildErrorMessage(e.getMessage());
        }
    }
}
