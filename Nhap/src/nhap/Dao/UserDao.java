/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhap.Dao;

import java.sql.*;
import nhap.DTO.User;
import nhap.Utils.PasswordUtils;

/**
 *
 * @author ADMIN
 */
public class UserDao {
    public User login(String name, String password){
        String sql ="SELECT * FROM USER WHERE USERNAME=? AND PASSWORD=? ";
        try(Connection conn = ConnectJDBC.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, name);
            pstm.setString(2, PasswordUtils.md5(password));
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
