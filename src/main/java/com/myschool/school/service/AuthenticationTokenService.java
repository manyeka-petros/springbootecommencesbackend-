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
    AuthenticationTokenRepository authenticationTokenRepository;
    public void confirmToken(AuthenticationToken authenticationToken) {
        authenticationTokenRepository.save(authenticationToken);
    }

    public AuthenticationToken findToken(Users users) {
        return authenticationTokenRepository.findTokenByUsers(users);
    }
    public  Users getUsers(String token){
        final  AuthenticationToken authenticationToken = authenticationTokenRepository.findByToken(token);
        if (Objects.isNull(token)){
            return null;
        }

        return authenticationToken.getUsers();
    }

    public void authenticateToken(String token ){
        if (Objects.isNull(token)) {
            throw  new AuthenticationFailException("token is not found ");
        }
        if(Objects.isNull(getUsers(token))){
            throw new AuthenticationFailException("token is not valid");
        }
    }
}
