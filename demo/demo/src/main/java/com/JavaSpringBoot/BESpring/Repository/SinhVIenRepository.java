package com.JavaSpringBoot.BESpring.Repository;

import com.JavaSpringBoot.BESpring.Entity.SinhVienEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SinhVIenRepository extends JpaRepository<SinhVienEntity,Integer> {
    Page<SinhVienEntity> findByTenContaining(String ten, Pageable pageable);
    @Query("""
        SELECT s.avatar FROM SinhVienEntity s WHERE s.avatar IS NOT NULL
    """)
    List<String> getAllAvatar();
    @Query(""" 
        SELECT AVG(s.dtb) FROM SinhVienEntity s
    """)
    Double getAvgSinhVien();
    @Query(""" 
        SELECT COUNT(s) FROM SinhVienEntity s WHERE s.dtb >= 8
    """)
    Long getSinhVienGioi();
    @Query(""" 
        SELECT MAX(s.dtb) FROM SinhVienEntity s
    """)
    Double getDiemCaoNhat();
}
