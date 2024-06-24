package com.myschool.school.repository;

import com.myschool.school.model.Cart;
import com.myschool.school.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    List<Cart> findAllByUsersOrderByCreatedDateDesc(Users users);
}
