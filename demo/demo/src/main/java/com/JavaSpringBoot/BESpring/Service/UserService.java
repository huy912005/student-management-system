package com.JavaSpringBoot.BESpring.Service;

import com.JavaSpringBoot.BESpring.DTO.Request.RefreshTokenRequest;
import com.JavaSpringBoot.BESpring.DTO.Request.UserLoginRequest;
import com.JavaSpringBoot.BESpring.DTO.Response.ApiResponse;
import com.JavaSpringBoot.BESpring.DTO.Response.RefreshTokenResponse;
import com.JavaSpringBoot.BESpring.DTO.Response.UserResponse;

import java.util.List;

public interface UserService {
    ApiResponse<List<UserResponse>> getAllUser();
    ApiResponse<UserResponse> create(UserLoginRequest req);
    ApiResponse<RefreshTokenResponse> refreshToken(String  refreshToken);
}
