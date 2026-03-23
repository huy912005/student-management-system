/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhap.Dao;

import java.sql.*;
import java.util.ArrayList;
import nhap.SinhVien1;

/**
 *
 * @author ADMIN
 */
public class SinhVienDAO {
    public void themSV(SinhVien1 sv){
        String sql = "insert into sinhvien(ten, tuoi, dtb) VALUES(?,?,?)";
        try(Connection conn = ConnectJDBC.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, sv.getTen());
            pstm.setInt(2, sv.getTuoi());
            pstm.setDouble(3, sv.getDtb());
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<SinhVien1> getAll(){
        ArrayList<SinhVien1> list = new ArrayList<>();
        String sql="Select * from sinhvien";
        try(Connection conn = ConnectJDBC.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {                
                SinhVien1 sv = new SinhVien1();
                sv.setId(rs.getInt("id"));
                sv.setTen(rs.getString("ten"));
                sv.setTuoi(Integer.parseInt(rs.getString("tuoi")));
                sv.setDtb(Double.parseDouble(rs.getString("dtb")));
                list.add(sv);
            }
        } catch (Exception e) {
            System.out.println("Loi!");
        }
        return list;
    }
    public boolean update(SinhVien1 sv){
        String sql ="UPDATE sinhvien SET ten=?, tuoi=?, dtb=? WHERE id=?";
        try (Connection conn = ConnectJDBC.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, sv.getTen());
            pstm.setInt(2, sv.getTuoi());
            pstm.setDouble(3, sv.getDtb());
            pstm.setInt(4, sv.getId());
            
            return pstm.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean delete(int id){
        String sql = "delete from sinhvien where id = ?";
        try (Connection conn = ConnectJDBC.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setInt(1, id);
            return pstm.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
