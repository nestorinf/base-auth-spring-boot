package com.base.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class UserRequestDto {

    @NotNull(message = "{user.firstname.not.null}")
    @NotEmpty(message = "{user.firstname.not.empty}")
    private String firstname;
    @NotEmpty(message = "{user.lastname.not.empty}")
//    @NotNull
    private String lastname;
    private String username;
    @NotNull
    @NotEmpty
    private String email;
//    @NotNull
    @NotEmpty(message = "{user.password.not.empty}")
    private String  password;

    private List<String> roles;

    public UserRequestDto(String firstname, String lastname, String username, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
