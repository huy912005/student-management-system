/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhap;

import nhap.DTO.SinhVien1;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class QuanLySinhVien {
    private ArrayList<SinhVien1> ds = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    
    public QuanLySinhVien() {
    }

    public ArrayList<SinhVien1> getSv() {
        return ds;
    }

    public void setSv(ArrayList<SinhVien1> sv) {
        if(sv!=null)
            this.ds = sv;
        else
            System.out.println("Danh sach rong!");
    }
    
    public SinhVien1 timSV(String ten){
        for(SinhVien1 sv1 : ds){
            if(sv1.getTen().equalsIgnoreCase(ten))
                return sv1;
        }
        return null;
    }
    
    public void sapXepDS(){
        ds.sort((a,b)->Double.compare(a.getDtb(), b.getDtb()));
    }
    
    public void themSV(SinhVien1 sv ){
        ds.add(sv);
    }
    
    public void xoaSVTheoTen(String sv){
        boolean check = ds.removeIf(filter->filter.getTen().equalsIgnoreCase(sv));
        if(check)
            System.out.println("Da xoa");
        else
            System.out.println("Loi khi xoa");
    }
    
    public void ghiFile(){
        try {
            FileWriter fileWriter = new FileWriter("F:\\2025-2026\\2026\\javasinhvien.txt");
            for(SinhVien1 sv : ds){
                fileWriter.write(sv.getTen()+","+sv.getTuoi()+","+sv.getDtb()+"\n");
            }
            fileWriter.close();
            System.out.println("Da luu file");
        } catch (IOException ex) {
            System.getLogger(QuanLySinhVien.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
    public void docFile(){
        ds.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader("F:\\2025-2026\\2026\\javasinhvien.txt"));
            String line;
            while((line = br.readLine())!=null){
                String []tach= line.split(",");
                String ten = tach[0];
                int tuoi =Integer.parseInt(tach[1]);
                double dtb =Double.parseDouble(tach[2]);
                SinhVien1 sv = new SinhVien1();
                sv.setTen(ten);
                sv.setDtb(dtb);
                sv.setTuoi(tuoi);
                ds.add(sv);
            }
            br.close();
            System.out.println("Da doc file!");
        } catch (Exception ex) {
            System.getLogger(QuanLySinhVien.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
