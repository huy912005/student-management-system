package com.JavaSpringBoot.BESpring.Controller;

import com.JavaSpringBoot.BESpring.DTO.Request.UserLoginRequest;
import com.JavaSpringBoot.BESpring.Entity.UserEnitity;
import com.JavaSpringBoot.BESpring.Security.TokenBlackList;
import com.JavaSpringBoot.BESpring.Service.UserService;
import com.JavaSpringBoot.BESpring.Utils.JwtUtil;
import com.JavaSpringBoot.BESpring.converter.UserMapper;
import com.JavaSpringBoot.BESpring.DTO.Response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
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
        String token = JwtUtil.generateToken(user.getUsername(),user.getRole());
        return new ApiResponse<>(true, "Login success",UserMapper.toLoginResponse(user, token));
    }

    @GetMapping
    public ApiResponse<?> getAll(){
        return new ApiResponse<>(true, "Success",userService.getAllUser());
    }

    @PostMapping
    public ApiResponse<?> addUser(@Valid @RequestBody UserLoginRequest req){
        return new ApiResponse<>(true, "Created",userService.create(req));
    }

    @PostMapping("/logout")
    public ApiResponse<?> logout(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        System.out.println("header : "+header);
        if(header!=null && header.startsWith("Bearer ")){
            String token = header.substring(7);
            TokenBlackList.add(token);
        }
        return new ApiResponse<>(true, "Logout Success",null);
    }
}