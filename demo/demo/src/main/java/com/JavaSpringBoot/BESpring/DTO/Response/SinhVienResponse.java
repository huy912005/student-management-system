package com.JavaSpringBoot.BESpring.DTO.Response;

public class SinhVienResponse {
    private int id;
    private String ten;
    private int tuoi;
    private double dtb;

    public SinhVienResponse(int id, String ten, int tuoi, double dtb) {
        this.id = id;
        this.ten = ten;
        this.tuoi = tuoi;
        this.dtb = dtb;
    }

    public int getId() { return id; }
    public String getTen() { return ten; }
    public int getTuoi() { return tuoi; }
    public double getDtb() { return dtb; }
}