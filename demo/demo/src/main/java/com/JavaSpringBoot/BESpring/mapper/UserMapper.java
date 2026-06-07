package com.JavaSpringBoot.BESpring.mapper;

import com.JavaSpringBoot.BESpring.DTO.Response.UserResponse;
import com.JavaSpringBoot.BESpring.Entity.UserEnitity;

public class UserMapper {

    public static UserResponse toResponse(UserEnitity user){
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setUsername(user.getUsername());
        res.setRole(user.getRole());
        return res;
    }

    public static UserResponse toLoginResponse(UserEnitity user, String token){
        return new UserResponse(user.getUsername(), user.getRole(), token);
    }
}