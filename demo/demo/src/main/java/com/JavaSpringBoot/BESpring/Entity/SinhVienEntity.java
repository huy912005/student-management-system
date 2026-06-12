package com.JavaSpringBoot.BESpring.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="sinhvien")
@AllArgsConstructor
@NoArgsConstructor
public class SinhVienEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ten;
    private int tuoi;
    private double dtb;
    private String avatar;
    private LocalDateTime createdAt;
}
