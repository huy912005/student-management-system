package com.JavaSpringBoot.BESpring.DTO.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginRequest {
    @NotBlank(message = "Tên không được rỗng!")
    private String username;
    @NotBlank(message = "Mật khẩu không được rỗng!")
    @Size(min = 3,message = "Mật khẩu hơn 3 ký tự")
    private String password;
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
