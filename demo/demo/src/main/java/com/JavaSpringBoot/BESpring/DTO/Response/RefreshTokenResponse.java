package com.JavaSpringBoot.BESpring.DTO.Response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RefreshTokenResponse {
    private String accessToken;
}
