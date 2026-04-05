package com.JavaSpringBoot.BESpring.Repository;

import com.JavaSpringBoot.BESpring.Entity.UserEnitity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEnitity,Long> {
//    UserEnitity findByUsernameAndPassword(String username, String password);
    UserEnitity findByUsername(String username);
}

