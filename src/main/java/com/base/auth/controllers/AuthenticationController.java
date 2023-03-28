package com.base.auth.controllers;

import com.base.auth.dto.LoginRequestDto;
import com.base.auth.dto.LoginResponseDto;
import com.base.auth.dto.UserRequestDto;
import com.base.auth.jwt.JwtUtils;
import com.base.auth.models.Role;
import com.base.auth.models.User;
import com.base.auth.services.UserDetailsImpl;
import com.base.auth.repository.RoleRepository;
import com.base.auth.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final static String ROLE_PREFIX = "ROLE_";
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    MessageSource messageSource;
    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDto user) {
//        System.out.println(messageSource.getMessage("firstname.not.empty", null, Locale.ENGLISH));
        Set<Role> roles = new HashSet<>();
        List<String> roleRequest = user.getRoles();
        roleRequest.stream().forEach( role -> {
            switch (role) {
                case "ADMIN":
                    Role roleAdmin = roleRepository.findRoleByName(ROLE_PREFIX+role);
                    roles.add(roleAdmin);
                    break;
                case "USER":
                    Role roleUser = roleRepository.findRoleByName(ROLE_PREFIX+role);
                    roles.add(roleUser);
                    break;
                default:
                    Role otherRole = roleRepository.findRoleByName(ROLE_PREFIX+role);
                    roles.add(otherRole);
                    break;
            }
        });
        User userCreate = new User(
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                passwordEncoder.encode(user.getPassword())
        );
        userCreate.setRoles(roles);
        userRepository.save(userCreate);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto login)  {
        LoginResponseDto dataAccess;
        System.out.println(userRepository.findByUsername(login.getUsername()));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        dataAccess = new LoginResponseDto(token, userDetails.getId(), userDetails.getUsername(), roles);
        return new ResponseEntity<>(dataAccess , HttpStatus.OK);

    }
}
