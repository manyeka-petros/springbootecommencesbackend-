package com.myschool.school.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Long id;
    private String token;
    private Date dateCreated;

    @OneToOne(targetEntity = Users.class,fetch = FetchType.EAGER)
            @JoinColumn(name = "usersId",nullable = false)
   private Users users;

    public  AuthenticationToken(Users users){
        this.users = users;
        this.dateCreated = new Date();
        this.token = UUID.randomUUID().toString();
    }
}
