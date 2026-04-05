package com.JavaSpringBoot.BESpring.DTO.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class SinhVienRequest {
    private int id;
    @NotBlank(message = "Tên không được rỗng!")
    private String ten;
    @Min(value = 1,message = "Tuổi > 0")
    private int tuoi;
    @Min(value = 0,message = "Điểm >= 0")
    @Max(value = 10,message = "Điểm <= 10")
    private double dtb;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }
    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public double getDtb() {
        return dtb;
    }
    public void setDtb(double dtb) {
        this.dtb = dtb;
    }
}
