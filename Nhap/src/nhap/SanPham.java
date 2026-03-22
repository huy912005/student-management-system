/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhap;

import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class SanPham {
    private String tenSP;
    private double giaSP;
    private static Scanner sc = new Scanner(System.in);

    public SanPham(String tenSP, double giaSP) {
        this.tenSP = tenSP;
        this.giaSP = giaSP;
    }

    public SanPham() {
    }

    public String getTenSP() {
        return tenSP;
    }

    public static Scanner getSc() {
        return sc;
    }
    
    public void setTenSP(String tenSP) {
        if(tenSP==null||tenSP.isEmpty())
            System.out.println("Ban hay nhap ten san pham");
        else
            this.tenSP = tenSP;
    }

    public double getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(double giaSP) {
        if(giaSP<0)
            System.out.println("Gia khong the am!");
        else
            this.giaSP = giaSP;
    }
    
    public void nhap(){
        System.out.println("Ten san pham : ");
        tenSP=sc.nextLine();
        System.out.println("Gia : ");
        giaSP=sc.nextDouble();
    }
    public void in(){
        System.out.println("Ten san pham : "+tenSP);
        System.out.println("Gia san pham : "+giaSP);
    }
    public double giamGia(int phanTram){
        if(phanTram<0 || phanTram>100)
            return 0;
        return giaSP*(1-phanTram/100.0);
    }
}
