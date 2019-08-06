package com.auth.jwt.dao;

import com.auth.jwt.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUsersDao extends JpaRepository<ApplicationUser,Integer>{

    ApplicationUser findByUserName(String username);
}
