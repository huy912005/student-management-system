package com.JavaSpringBoot.BESpring.Service;

import com.JavaSpringBoot.BESpring.Entity.UserEnitity;
import com.JavaSpringBoot.BESpring.Repository.UserRepository;
import com.JavaSpringBoot.BESpring.Utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public UserEnitity login(String name, String pwd){
        if(name.isEmpty()||pwd.isEmpty()){
            System.out.println("name va password ko de trong!");
            return null;
        }
        String hashed = PasswordUtils.md5(pwd);
        return userRepository.findByUsernameAndPassword(name,hashed);
    }
}
