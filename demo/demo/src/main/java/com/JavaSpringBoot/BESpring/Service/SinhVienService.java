package com.JavaSpringBoot.BESpring.Service;

import com.JavaSpringBoot.BESpring.Entity.SinhVienEntity;
import com.JavaSpringBoot.BESpring.Repository.SinhVIenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinhVienService {
    @Autowired
    private SinhVIenRepository sinhVIenRepository;
    public List<SinhVienEntity>getall(){
        if(sinhVIenRepository.count()>0){
            return sinhVIenRepository.findAll();
        }
        return null;
    }
    public SinhVienEntity save(SinhVienEntity sinhVienEntity){
        if(sinhVienEntity.getTen()==null ||sinhVienEntity.getTen()==""){
            throw new IllegalArgumentException("Tên không được trống!");
        }
        if(sinhVIenRepository.count()>0){
            return sinhVIenRepository.save(sinhVienEntity);
        }
        return null;
    }
    public void delete(int id){
        sinhVIenRepository.deleteById(id);
    }
}
