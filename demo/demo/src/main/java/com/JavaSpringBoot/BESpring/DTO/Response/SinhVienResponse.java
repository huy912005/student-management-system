package com.JavaSpringBoot.BESpring.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SinhVienResponse {
    private int id;
    private String ten;
    private int tuoi;
    private double dtb;
    private String avatar;
    private LocalDateTime createdAt;
}