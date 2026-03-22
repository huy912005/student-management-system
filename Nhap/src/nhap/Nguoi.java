/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhap;

/**
 *
 * @author ADMIN
 */
public class Nguoi {
    protected String ten;
    protected int tuoi;

    public Nguoi() {
    }

    public Nguoi(String ten, int tuoi) {
        this.ten = ten;
        this.tuoi = tuoi;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        if(ten==null||ten.isEmpty())
            System.out.println("Vui long nhap ten!");
        else
            this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        if(tuoi<=0)
            System.out.println("Tuoi phai la so duong");
        else
            this.tuoi = tuoi;
    }
    
}


