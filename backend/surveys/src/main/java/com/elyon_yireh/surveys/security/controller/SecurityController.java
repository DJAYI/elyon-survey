package com.elyon_yireh.surveys.security.controller;

import com.elyon_yireh.surveys.model.entities.RespondentEntity;
import com.elyon_yireh.surveys.security.dto.AuthLoginRequest;
import com.elyon_yireh.surveys.security.dto.AuthResponse;
import com.elyon_yireh.surveys.security.entities.UserEntity;
import com.elyon_yireh.surveys.security.repository.UserRepository;
import com.elyon_yireh.surveys.security.services.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth")
@PreAuthorize("denyAll()")
public class SecurityController {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserEntity> getUsers () {
        return  userRepository.findAll();
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }

}
