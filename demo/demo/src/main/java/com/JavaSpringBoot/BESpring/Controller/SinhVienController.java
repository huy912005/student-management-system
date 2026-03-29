package com.JavaSpringBoot.BESpring.Controller;

import com.JavaSpringBoot.BESpring.Entity.SinhVienEntity;
import com.JavaSpringBoot.BESpring.Service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sinhvien")
public class SinhVienController {
    @Autowired
    private SinhVienService sinhVienService;
    @GetMapping
    public List<SinhVienEntity>  findAll(){
        if(sinhVienService.getall().size()>0){
            return sinhVienService.getall();
        }
        return null;
    }
    @PostMapping
    public SinhVienEntity create(@RequestBody SinhVienEntity sv){
        if(sv.getTen() == null || sv.getTen().isEmpty())
            throw new IllegalArgumentException("Tên không được rỗng");
        return sinhVienService.save(sv);
    }
    @PutMapping("/{id}")
    public SinhVienEntity update(@PathVariable int id, @RequestBody SinhVienEntity sv){
        sv.setId(id);
        return sinhVienService.save(sv);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        sinhVienService.delete(id);
    }
}
