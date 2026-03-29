package com.JavaSpringBoot.BESpring.Controller;

import com.JavaSpringBoot.BESpring.Entity.UserEnitity;
import com.JavaSpringBoot.BESpring.Service.UserService;
import com.JavaSpringBoot.BESpring.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/auth/login")
    public String login(@RequestBody UserEnitity user){
        UserEnitity userEnitity = userService.login(user.getUsername(),user.getPassword());
        if(userEnitity==null){
            throw new RuntimeException("Sai tài khoản!");
        }
        return JwtUtil.generateToken(userEnitity.getUsername());
    }
}
