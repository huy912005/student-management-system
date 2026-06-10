package com.JavaSpringBoot.BESpring.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {
    private Long tongSinhVien;
    private Double dtb;
    private Long sinhVienGioi;
    private Double diemCaoNhat;
}
