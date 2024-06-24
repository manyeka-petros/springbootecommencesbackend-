package com.myschool.school.repository;

import com.myschool.school.model.AuthenticationToken;
import com.myschool.school.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findTokenByUsers(Users users);
}
