/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhap.Service;

import java.util.ArrayList;
import nhap.Dao.SinhVienDAO;
import nhap.DTO.SinhVien1;

/**
 *
 * @author ADMIN
 */
public class SinhVienService {
    private SinhVienDAO svdao=new SinhVienDAO();
    public void themSV(SinhVien1 sv){
        if(sv==null)
            throw new IllegalArgumentException("Sinh vien null!");
        else
            svdao.insert(sv);
    }
    public ArrayList<SinhVien1>getAll(){
        ArrayList<SinhVien1> listSV=svdao.getAll();
        if(listSV==null)
            return null;
        return listSV;
    }
    public boolean updateSV(SinhVien1 sv){
        if(sv.getTen().isEmpty()){
            throw new IllegalArgumentException("Ten khong duoc rong!");
        }
        return svdao.update(sv);
    }
    public boolean deleteSV(int id){
        if(id<=0)
            return false;
        return svdao.delete(id);
    }
    public ArrayList<SinhVien1>searchByName(String name){
        if(name==null)
            return new ArrayList<>();
        return svdao.searchByName(name);
    }
    public ArrayList<SinhVien1>sapXep(int so){
        if(so<1 || so>3)
            return new ArrayList<>();
        return svdao.sortBy(so);
    }
}
