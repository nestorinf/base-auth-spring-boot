package com.base.auth.dto;

import java.util.List;

public class LoginResponseDto {
    private String accessToken;
    private String userId;
    private String username;
    private List<String> roles;

    public LoginResponseDto(String accessToken, String userId, String username, List<String> roles) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.username = username;
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
