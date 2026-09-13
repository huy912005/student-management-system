package com.JavaSpringBoot.BESpring.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class RefreshTokenRequest {
    @NotBlank(message = "refreshToken không được trống")
    private String refreshToken;
}
