package com.JavaSpringBoot.BESpring.DTO.Response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecentSinhVienResponse {
    private String ten;
    private LocalDateTime createdAt;
}
