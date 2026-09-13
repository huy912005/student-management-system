package com.JavaSpringBoot.BESpring.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String userName;
    private String role;
    private String accessToken;
    private String refreshToken;
}
