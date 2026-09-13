package com.JavaSpringBoot.BESpring.DTO.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SinhVienRequest {
    private int id;
    @NotBlank(message = "Tên không được rỗng!")
    private String ten;
    @Min(value = 1,message = "Tuổi > 0")
    private int tuoi;
    @Min(value = 0,message = "Điểm >= 0")
    @Max(value = 10,message = "Điểm <= 10")
    private double dtb;
    private String avatar;
    private LocalDateTime createdAt;
}
