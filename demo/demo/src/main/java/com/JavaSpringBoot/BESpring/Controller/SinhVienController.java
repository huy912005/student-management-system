package com.JavaSpringBoot.BESpring.Controller;

import com.JavaSpringBoot.BESpring.DTO.Request.SinhVienRequest;
import com.JavaSpringBoot.BESpring.DTO.Response.SinhVienResponse;
import com.JavaSpringBoot.BESpring.DTO.Response.page.PageResponse;
import com.JavaSpringBoot.BESpring.Service.ISinhVienService;
import com.JavaSpringBoot.BESpring.DTO.Response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sinhvien")
public class SinhVienController {
    @Autowired
    private ISinhVienService sinhVienService;
    @GetMapping
    public ApiResponse<PageResponse<SinhVienResponse>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        return new ApiResponse<>(true, "Get list success", sinhVienService.getAll(page, size));
    }
    @PostMapping
    public ApiResponse<?> create(@Valid @RequestBody SinhVienRequest sv){
        return new ApiResponse<>(true,"Created",sinhVienService.save(sv));
    }
    @PutMapping("/{id}")
    public ApiResponse<?> update(@PathVariable int id, @RequestBody SinhVienRequest sv){
        sv.setId(id);
        return new ApiResponse<>(true,"Updated",sinhVienService.update(id,sv));
    }
    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable int id){
        sinhVienService.delete(id);
        return new ApiResponse<>(true, "Deleted", null);
    }
    @GetMapping("/search")
    public ApiResponse<?> search(@RequestParam String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        return new ApiResponse<>(true, "Search success", sinhVienService.search(name, page, size));
    }
}
