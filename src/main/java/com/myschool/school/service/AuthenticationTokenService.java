package com.myschool.school.service;

import com.myschool.school.model.AuthenticationToken;
import com.myschool.school.model.Users;
import com.myschool.school.repository.AuthenticationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {

    @Autowired
    AuthenticationTokenRepository authenticationTokenRepository;
    public void confirmToken(AuthenticationToken authenticationToken) {
        authenticationTokenRepository.save(authenticationToken);
    }

    public AuthenticationToken findToken(Users users) {
        return authenticationTokenRepository.findTokenByUsers(users);
    }
}
