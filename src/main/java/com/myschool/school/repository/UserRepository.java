package com.myschool.school.repository;

import com.myschool.school.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    Users findByEmail(String email);
}
