package com.JavaSpringBoot.BESpring.Controller;

import com.JavaSpringBoot.BESpring.DTO.Request.UserLoginRequest;
import com.JavaSpringBoot.BESpring.Entity.UserEnitity;
import com.JavaSpringBoot.BESpring.Service.UserService;
import com.JavaSpringBoot.BESpring.Utils.JwtUtil;
import com.JavaSpringBoot.BESpring.converter.UserMapper;
import com.JavaSpringBoot.BESpring.DTO.Response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/login")
    public ApiResponse<?> login(@Valid @RequestBody UserLoginRequest request){
        UserEnitity user = userService.login(
                request.getUsername(),
                request.getPassword()
        );
        String token = JwtUtil.generateToken(user.getUsername());
        return new ApiResponse<>(UserMapper.toLoginResponse(user, token), "Login success");
    }

    @GetMapping
    public ApiResponse<?> getAll(){
        return new ApiResponse<>(userService.getAllUser(), "Success");
    }

    @PostMapping
    public ApiResponse<?> addUser(@Valid @RequestBody UserLoginRequest req){
        return new ApiResponse<>(userService.create(req), "Created");
    }
}