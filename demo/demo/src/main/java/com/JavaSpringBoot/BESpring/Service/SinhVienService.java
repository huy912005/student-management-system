package com.JavaSpringBoot.BESpring.Service;

import com.JavaSpringBoot.BESpring.DTO.Request.SinhVienRequest;
import com.JavaSpringBoot.BESpring.DTO.Response.SinhVienResponse;
import com.JavaSpringBoot.BESpring.DTO.Response.UserResponse;
import com.JavaSpringBoot.BESpring.Entity.SinhVienEntity;
import com.JavaSpringBoot.BESpring.Repository.SinhVIenRepository;
import com.JavaSpringBoot.BESpring.converter.SinhVienMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinhVienService {
    @Autowired
    private SinhVIenRepository sinhVIenRepository;
    public List<SinhVienResponse> getAll(){
        List<SinhVienEntity> list = sinhVIenRepository.findAll();

        return list.stream()
                .map(SinhVienMapper::toResponse)
                .toList();
    }
    public SinhVienResponse save(SinhVienRequest sinhVienRequest){
        if(sinhVienRequest.getTen()==null ||sinhVienRequest.getTen()==""){
            throw new IllegalArgumentException("Tên không được trống!");
        }
        SinhVienEntity entity = SinhVienMapper.toEntity(sinhVienRequest);
        SinhVienEntity saved = sinhVIenRepository.save(entity);
        return SinhVienMapper.toResponse(saved);
    }

    public SinhVienResponse update(int id,SinhVienRequest sinhVienRequest){
        SinhVienEntity  entity = sinhVIenRepository.findById(id).orElseThrow(()->new RuntimeException("Không tìm thấy"));
        if(sinhVienRequest.getTen()!=null)
            entity.setTen(sinhVienRequest.getTen());
        if(sinhVienRequest.getDtb()!=0)
            entity.setDtb(sinhVienRequest.getDtb());
        if(sinhVienRequest.getTuoi()!=0)
            entity.setTuoi(sinhVienRequest.getTuoi());
        entity = sinhVIenRepository.save(entity);
        return SinhVienMapper.toResponse(sinhVIenRepository.save(entity));
    }

    public void delete(int id){
        sinhVIenRepository.deleteById(id);
    }
}
