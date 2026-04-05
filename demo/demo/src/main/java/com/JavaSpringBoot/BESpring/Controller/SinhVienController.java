package com.JavaSpringBoot.BESpring.Controller;

import com.JavaSpringBoot.BESpring.DTO.Request.SinhVienRequest;
import com.JavaSpringBoot.BESpring.Service.SinhVienService;
import com.JavaSpringBoot.BESpring.DTO.Response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sinhvien")
public class SinhVienController {
    @Autowired
    private SinhVienService sinhVienService;
    @GetMapping
    public ApiResponse<?> findAll(){
        if(sinhVienService.getAll().size()>0){
            return new ApiResponse<>(sinhVienService.getAll(),"Success");
        }
        return null;
    }
    @PostMapping
    public ApiResponse<?> create(@Valid @RequestBody SinhVienRequest sv){
        if(sv.getTen() == null || sv.getTen().isEmpty())
            throw new IllegalArgumentException("Tên không được rỗng");
        return new ApiResponse<>(sinhVienService.save(sv),"Created");
    }
    @PutMapping("/{id}")
    public ApiResponse<?> update(@PathVariable int id, @RequestBody SinhVienRequest sv){
        sv.setId(id);
        return new ApiResponse<>(sinhVienService.update(id,sv),"Updated");
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        sinhVienService.delete(id);
    }
}
