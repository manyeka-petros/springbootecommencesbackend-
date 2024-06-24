package com.myschool.school.repository;

import com.myschool.school.model.Users;
import com.myschool.school.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository  extends JpaRepository<WishList,Long> {

    List<WishList> findAllByUsersByCreateDate(Users users);
}
