/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhap.Dao;
import java.sql.*;
import java.sql.DriverManager;
/**
 *
 * @author ADMIN
 */
public class ConnectJDBC {
    private static String DB_URL = "jdbc:mysql://localhost:3306/qlsv";
    private static String USER_NAME = "root";
    private static String PASSWORD = "12345";
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Loi ket noi!");
            return null;
        }
    }
}
