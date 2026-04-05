package com.JavaSpringBoot.BESpring.DTO.Response;

public class UserResponse {
    private int id;
    private String username;
    private String role;
    private String token;

    public UserResponse() {}

    public UserResponse(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public UserResponse(String username, String role, String token) {
        this.username = username;
        this.role = role;
        this.token = token;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
