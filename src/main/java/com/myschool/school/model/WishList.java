package com.myschool.school.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createDate;

    @OneToOne(targetEntity = Users.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "usersId",nullable = false)
    Users users;

    @ManyToOne
    @JoinColumn(name = "productId")
    Product product;

    public WishList(Users users, Product product) {
        this.users = users;
        this.product = product;
        this.createDate = new Date();
    }
}
