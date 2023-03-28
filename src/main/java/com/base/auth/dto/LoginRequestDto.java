package com.base.auth.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class LoginRequestDto {

    @NotEmpty(message = "username is required")
    @NotNull()
    private String username;
    private String email;
    @NotEmpty(message = "password is required")
    @NotNull()
    private String password;
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
