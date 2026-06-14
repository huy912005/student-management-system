package com.JavaSpringBoot.BESpring.mapper;

import com.JavaSpringBoot.BESpring.DTO.Request.SinhVienRequest;
import com.JavaSpringBoot.BESpring.DTO.Response.SinhVienResponse;
import com.JavaSpringBoot.BESpring.DTO.Response.TopSinhVienResponse;
import com.JavaSpringBoot.BESpring.Entity.SinhVienEntity;

public class SinhVienMapper {

    public static SinhVienResponse toResponse(SinhVienEntity sv){
        return new SinhVienResponse(
                sv.getId(),
                sv.getTen(),
                sv.getTuoi(),
                sv.getDtb(),
                sv.getAvatar(),
                sv.getCreatedAt()
        );
    }

    public static SinhVienEntity toEntity(SinhVienRequest req){
        SinhVienEntity sv = new SinhVienEntity();
        sv.setId(req.getId());
        sv.setTen(req.getTen());
        sv.setTuoi(req.getTuoi());
        sv.setDtb(req.getDtb());
        sv.setAvatar(req.getAvatar());
        sv.setCreatedAt(req.getCreatedAt());
        return sv;
    }
    public static TopSinhVienResponse toTopSinhVienResponse(SinhVienEntity sv){
        return new TopSinhVienResponse(sv.getTen(),sv.getDtb());
    }
}
