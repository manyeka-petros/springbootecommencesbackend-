package com.myschool.school.service;

import com.myschool.school.configure.EncryptingPassword;
import com.myschool.school.dto.user.ResponseDto;
import com.myschool.school.dto.user.SignInDto;
import com.myschool.school.dto.user.SignInResponseDto;
import com.myschool.school.dto.user.SignUpDto;
import com.myschool.school.exception.AuthenticationFailException;
import com.myschool.school.exception.CustomException;
import com.myschool.school.model.AuthenticationToken;
import com.myschool.school.model.Users;
import com.myschool.school.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.net.openssl.ciphers.MessageDigest;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @Transactional
    public ResponseDto createAccount(SignUpDto signUpDto) {

        if (!Objects.isNull(userRepository.findByEmail(signUpDto.getEmail()))){

            throw new ClassCastException("user is already available in the system");

        }

        //encrypte the password

        String encryptPassword = EncryptingPassword.encryptPassword(signUpDto.getPassword());


        // create user

        Users  user = new Users(signUpDto.getFirstName(), signUpDto.getLastName(), signUpDto.getEmail(),encryptPassword);

        userRepository.save(user);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationTokenService.confirmToken(authenticationToken);

        ResponseDto responseDto = new ResponseDto("success","good response");

        return  responseDto;
    }


    public SignInResponseDto signIn(SignInDto signInDto) throws AuthenticationException, NoSuchAlgorithmException {

        Users users = userRepository.findByEmail(signInDto.getEmail());
        if(Objects.isNull(users)){
            throw new AuthenticationException("user is not in the system");
        }

        String hashesPassword = EncryptingPassword.encryptPassword(signInDto.getPassword());
        System.out.println("Hashed Input Password: " + hashesPassword);
        System.out.println("Stored Hashed Password: " + users.getPassword());

        if(!EncryptingPassword.verifyPassword(signInDto.getPassword(),users.getPassword())){
            throw new AuthenticationFailException("wrong password ");
        }
        //Retrieve the token from user

        AuthenticationToken  authenticationToken = authenticationTokenService.findToken(users);

        if(Objects.isNull(authenticationToken)){
            throw new CustomException("token is not present ");
        }

        return new SignInResponseDto("Signed In successfully ",authenticationToken.getToken());
    }
}
