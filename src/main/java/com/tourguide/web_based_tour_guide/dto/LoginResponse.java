package com.tourguide.web_based_tour_guide.dto;

public class LoginResponse {

    private String message;
    private Integer userId;
    private String fullName;
    private String role;
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(
            String message,
            Integer userId,
            String fullName,
            String role,
            String token) {

        this.message = message;
        this.userId = userId;
        this.fullName = fullName;
        this.role = role;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}