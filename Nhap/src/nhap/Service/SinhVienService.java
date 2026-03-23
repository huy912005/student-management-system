/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhap.Service;

import java.util.ArrayList;
import nhap.Dao.SinhVienDAO;
import nhap.SinhVien1;

/**
 *
 * @author ADMIN
 */
public class SinhVienService {
    private SinhVienDAO svdao=new SinhVienDAO();
    public void themSV(SinhVien1 sv){
        if(sv==null)
            System.out.println("Loi sinh vien rong!");
        else
            svdao.themSV(sv);
    }
    public ArrayList<SinhVien1>getAll(){
        ArrayList<SinhVien1> listSV=svdao.getAll();
        if(listSV==null)
            return null;
        return listSV;
    }
    public boolean updateSV(SinhVien1 sv){
        if(sv.getTen().isEmpty()){
            System.out.println("Ten khong duoc rong!");
            return false;
        }
        return svdao.update(sv);
    }
    public boolean deleteSV(int id){
        if(id<=0)
            return false;
        return svdao.delete(id);
    }
}
