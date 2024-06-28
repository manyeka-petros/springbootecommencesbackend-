package com.myschool.school.service;

import com.myschool.school.exception.AuthenticationFailException;
import com.myschool.school.model.AuthenticationToken;
import com.myschool.school.model.Users;
import com.myschool.school.repository.AuthenticationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationTokenService {

    @Autowired
    private AuthenticationTokenRepository authenticationTokenRepository;

    // Save a new authentication token
    public void confirmToken(AuthenticationToken authenticationToken) {
        authenticationTokenRepository.save(authenticationToken);
    }

    // Find an authentication token by user
    public AuthenticationToken findToken(Users users) {
        return authenticationTokenRepository.findTokenByUsers(users);
    }

    // Get user associated with a token
    public Users getUsers(String token){
        final AuthenticationToken authenticationToken = authenticationTokenRepository.findByToken(token);
        if (Objects.isNull(authenticationToken)){
            return null;
        }
        return authenticationToken.getUsers();
    }

    // Authenticate a token
    public void authenticateToken(String token) {
        if (Objects.isNull(token)) {
            throw new AuthenticationFailException("Token not found");
        }
        if (Objects.isNull(getUsers(token))) {
            throw new AuthenticationFailException("Invalid token");
        }
    }
}
