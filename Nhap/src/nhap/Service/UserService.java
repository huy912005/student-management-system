/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhap.Service;

import nhap.DTO.User;
import nhap.Dao.UserDao;

/**
 *
 * @author ADMIN
 */
public class UserService {
    private UserDao dao = new UserDao();
    public User login(String name, String password){
        if(name.isEmpty()||password.isEmpty()){
            System.out.println("name va password ko de trong!");
            return null;
        }
        return dao.login(name, password);
    }
}
