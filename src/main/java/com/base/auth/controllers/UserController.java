package com.base.auth.controllers;

import com.base.auth.models.User;
import com.base.auth.services.users.IUser;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    IUser userRepository;
    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/")
    public ResponseEntity<?> getUsers() {
        System.out.println("Test live reload");
        List<User> users = userRepository.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
