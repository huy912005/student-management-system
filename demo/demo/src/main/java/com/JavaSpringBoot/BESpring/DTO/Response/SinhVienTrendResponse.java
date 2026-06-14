package com.JavaSpringBoot.BESpring.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SinhVienTrendResponse {
    private String thang;
    private Long soLuong;
}
