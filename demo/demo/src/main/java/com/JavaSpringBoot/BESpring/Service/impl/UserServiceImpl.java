package com.JavaSpringBoot.BESpring.Service.impl;

import com.JavaSpringBoot.BESpring.DTO.Request.UserLoginRequest;
import com.JavaSpringBoot.BESpring.DTO.Response.ApiResponse;
import com.JavaSpringBoot.BESpring.DTO.Response.RefreshTokenResponse;
import com.JavaSpringBoot.BESpring.DTO.Response.UserResponse;
import com.JavaSpringBoot.BESpring.Entity.UserEnitity;
import com.JavaSpringBoot.BESpring.Exception.ResourceNotFoundException;
import com.JavaSpringBoot.BESpring.Repository.UserRepository;
import com.JavaSpringBoot.BESpring.Service.UserService;
import com.JavaSpringBoot.BESpring.Utils.JwtUtil;
import com.JavaSpringBoot.BESpring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserEnitity login(String username, String password){
        UserEnitity user = userRepository.findByUsername(username);
        if(user == null || !passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Sai tài khoản");
        }
        return user;
    }
    public ApiResponse<List<UserResponse> > getAllUser(){
        return new ApiResponse<>(true,"Lấy danh sách sinh viên thành công!",userRepository.findAll().stream().map(UserMapper::toResponse).toList());
    }
    public ApiResponse<UserResponse> create(UserLoginRequest req){
        UserEnitity user = new UserEnitity();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(req.getRole());
        UserEnitity saved = userRepository.save(user);
        return new ApiResponse<>(true,"Tạo sinh viên mới thành công!",UserMapper.toResponse(saved));
    }

    @Override
    public ApiResponse<RefreshTokenResponse> refreshToken(String refreshToken) {
        String userName = JwtUtil.getUsername(refreshToken);
        UserEnitity user = userRepository.findByUsername(userName);
        if(user==null)
            throw new ResourceNotFoundException("Không tìm thấy user");
        RefreshTokenResponse refreshTokenResponse = new RefreshTokenResponse();
        refreshTokenResponse.setAccessToken(JwtUtil.generateAccessToken(userName,user.getRole()));
        return new ApiResponse<>(true,"accessToken thành công",refreshTokenResponse);
    }
}