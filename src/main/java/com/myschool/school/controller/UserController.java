package com.myschool.school.controller;

import com.myschool.school.dto.user.ResponseDto;
import com.myschool.school.dto.user.SignInDto;
import com.myschool.school.dto.user.SignInResponseDto;
import com.myschool.school.dto.user.SignUpDto;
import com.myschool.school.service.UserService;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint for creating a new user account
    @PostMapping("/createAccount")
    public ResponseDto createUserAccount(@RequestBody SignUpDto signUpDto){
        return userService.createAccount(signUpDto);
    }

    // Endpoint for signing in an existing user
    @PostMapping("/signIn")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) throws AuthenticationException, NoSuchAlgorithmException {
        return userService.signIn(signInDto);
    }
}
