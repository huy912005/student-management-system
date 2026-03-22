/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package nhap;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Nhap {
    private static Scanner sc = new Scanner(System.in);
    private static QuanLySinhVien ql = new QuanLySinhVien();
   private static SinhVien1 nhap(){
        SinhVien1 sv=new SinhVien1();
        System.out.println("Nhap diem trung binh : ");
        sv.setDtb(Double.parseDouble(sc.nextLine()));
        System.out.println("Nhap ten : ");
        sv.setTen(sc.nextLine());
        System.out.println("Nhap tuoi : ");
        sv.setTuoi(Integer.parseInt(sc.nextLine()));
        return sv;
    }
    public static void in(SinhVien1 sv ){
        System.out.println("Ten : "+sv.getTen());
        System.out.println("Tuoi : "+sv.getTuoi());
        System.out.println("Diem trung binh : "+sv.getDtb());
        System.out.println("-------------------------------------------");
    }
    private static void nhapDS(){
        System.out.println("Nhap so luong sinh vien : ");
        int n = sc.nextInt();
        for(int i = 0;i<n;i++){
            SinhVien1 sv = nhap();
            ql.themSV(sv);
        }
    }
    private static void inDS(){
        for(SinhVien1 sv : ql.getSv()){
            in(sv);
        }
    }
    
    public static void main(String[] args) {
        int n=0;
        do{
            System.out.println("---------------");
            System.out.println("1.Nhap");
            System.out.println("2.In");
            System.out.println("3.Sap xep");
            System.out.println("4.Tim kiem");
            System.out.println("5.Them sv");
            System.out.println("6.Xoa sv");
            System.out.println("7.Doc file");
            System.out.println("8.Ghi file");
            System.out.println("0.Thoat");
            System.out.println("---------------");
            System.out.println("Xin moi lua chon : ");
            n = Integer.parseInt(sc.nextLine());
            switch (n) {
                case 1:
                    nhapDS();
                    break;
                case 2:
                    if(ql.getSv().isEmpty())
                        System.out.println("Danh sach rong!");
                    else
                        inDS();
                    break;
                case 3:
                    if(ql.getSv().isEmpty())
                        System.out.println("Danh sach rong!");
                    else
                        ql.sapXepDS();
                    break;
                case 4:
                    if(ql.getSv().isEmpty())
                        System.out.println("Danh sach rong!");
                    else{
                        sc.nextLine();
                        System.out.println("Nhap ten sinh vien can tim : ");
                        String ten = sc.nextLine();
                        SinhVien1 sv =ql.timSV(ten);
                        if(sv==null){
                            System.out.println("Khong tim thay sinh vien!");
                        }
                        else{
                            in(sv);
                        }
                    }
                    break;
                case 5:
                    SinhVien1 svt= nhap();
                    ql.themSV(svt);
                    break;
                case 6:
                    sc.nextLine();
                    System.out.println("Nhap ten sv can xoa : ");
                    String ten = sc.nextLine();
                    ql.xoaSVTheoTen(ten);
                    break;
                case 7:
                    ql.docFile();
                    break;
                case 8:
                    ql.ghiFile();
                    break;
                case 0:
                    System.out.println("Xin chao va hen gap lai!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }while(n!=0);
    }
}
