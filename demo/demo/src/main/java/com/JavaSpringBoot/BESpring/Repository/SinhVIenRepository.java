package com.JavaSpringBoot.BESpring.Repository;

import com.JavaSpringBoot.BESpring.Entity.SinhVienEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

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
        SELECT COUNT(s) FROM SinhVienEntity s WHERE s.dtb >= 6.5 AND s.dtb < 8
    """)
    Long getSinhVienKha();
    @Query(""" 
        SELECT COUNT(s) FROM SinhVienEntity s WHERE s.dtb >= 5 AND s.dtb < 6.5
    """)
    Long getSinhVienTrungBinh();
    @Query(""" 
        SELECT COUNT(s) FROM SinhVienEntity s WHERE s.dtb < 5
    """)
    Long getSinhVienYeu();
    @Query(""" 
        SELECT MAX(s.dtb) FROM SinhVienEntity s
    """)
    Double getDiemCaoNhat();
    List<SinhVienEntity> findTop5ByOrderByDtbDesc();
    @Query("""
        SELECT YEAR(s.createdAt),
        MONTH(s.createdAt),
        COUNT(s)
        FROM SinhVienEntity s
        GROUP BY YEAR(s.createdAt), MONTH(s.createdAt)
        ORDER BY YEAR(s.createdAt), MONTH(s.createdAt)
    """)
    List<Object[]> getSinhVienTheoThang();
    List<SinhVienEntity> findTop5ByOrderByCreatedAtDesc();
}
