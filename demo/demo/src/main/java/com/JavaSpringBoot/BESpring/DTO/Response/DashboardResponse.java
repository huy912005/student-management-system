package com.JavaSpringBoot.BESpring.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {
    private Long tongSinhVien;
    private Double dtb;
    private Long sinhVienGioi;
    private Long sinhVienKha;
    private Long sinhVienTrungBinh;
    private Long sinhVienYeu;
    private Double diemCaoNhat;
    List<TopSinhVienResponse> topSinhVien;
}
