/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhap.DTO;

import nhap.Nguoi;


/**
 *
 * @author ADMIN
 */
public class SinhVien1 extends Nguoi{
    private double dtb;

    public SinhVien1() {
    }

    public SinhVien1(String ten, int tuoi) {
        super(ten, tuoi);
    }

    public SinhVien1(double dtb, int id, String ten, int tuoi) {
        super(id, ten, tuoi);
        this.dtb = dtb;
    }

    public SinhVien1(double dtb, String ten, int tuoi) {
        super(ten, tuoi);
        this.dtb = dtb;
    }
    
    public double getDtb() {
        return dtb;
    }

    public void setDtb(double dtb) {
        if(dtb>=0&&dtb<=10)
            this.dtb = dtb;
        else
            System.out.println("Loi");
    }
    
}
