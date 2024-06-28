package com.myschool.school.service;

import com.myschool.school.dto.user.ResponseDto;
import com.myschool.school.dto.user.SignInDto;
import com.myschool.school.dto.user.SignInResponseDto;
import com.myschool.school.dto.user.SignUpDto;
import com.myschool.school.model.AuthenticationToken;
import com.myschool.school.model.Users;
import com.myschool.school.repository.UserRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationTokenService authenticationTokenService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Create a new user account
    public ResponseDto createAccount(SignUpDto signUpDto) {
        Users user = new Users(signUpDto.getFirstName(), signUpDto.getLastName(), signUpDto.getEmail(),
                passwordEncoder.encode(signUpDto.getPassword()));
        userRepository.save(user);

        AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationTokenService.confirmToken(authenticationToken);

        return new ResponseDto("Account created successfully", true);
    }

    // Sign in an existing user
    public SignInResponseDto signIn(SignInDto signInDto) throws AuthenticationException, NoSuchAlgorithmException {
        Users user = userRepository.findByEmail(signInDto.getEmail());
        if (user == null || !passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Invalid email or password");
        }

        AuthenticationToken authenticationToken = authenticationTokenService.findToken(user);
        if (authenticationToken == null) {
            authenticationToken = new AuthenticationToken(user);
            authenticationTokenService.confirmToken(authenticationToken);
        }

        return new SignInResponseDto("Login successful", authenticationToken.getToken());
    }
}
