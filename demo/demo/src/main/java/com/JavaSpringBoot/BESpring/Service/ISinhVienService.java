package com.JavaSpringBoot.BESpring.Service;

import com.JavaSpringBoot.BESpring.DTO.Request.SinhVienRequest;
import com.JavaSpringBoot.BESpring.DTO.Response.ApiResponse;
import com.JavaSpringBoot.BESpring.DTO.Response.DashboardResponse;
import com.JavaSpringBoot.BESpring.DTO.Response.SinhVienResponse;
import com.JavaSpringBoot.BESpring.DTO.Response.page.PageResponse;

public interface ISinhVienService {
    PageResponse<SinhVienResponse> getAll(int page, int size);
    SinhVienResponse save(SinhVienRequest sinhVienRequest);
    SinhVienResponse update(int id, SinhVienRequest sinhVienRequest);
    void delete(int id);
    PageResponse<SinhVienResponse> search(String name, int page, int size);
    ApiResponse<DashboardResponse> dashBoard();
}
