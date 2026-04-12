package com.JavaSpringBoot.BESpring.Repository;

import com.JavaSpringBoot.BESpring.Entity.SinhVienEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SinhVIenRepository extends JpaRepository<SinhVienEntity,Integer> {
    Page<SinhVienEntity> findByTenContaining(String ten, Pageable pageable);
}
