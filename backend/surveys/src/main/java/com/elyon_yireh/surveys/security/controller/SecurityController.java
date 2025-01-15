package com.elyon_yireh.surveys.security.controller;

import com.elyon_yireh.surveys.security.dto.AuthLoginRequest;
import com.elyon_yireh.surveys.security.dto.AuthResponse;
import com.elyon_yireh.surveys.security.entities.UserEntity;
import com.elyon_yireh.surveys.security.repository.UserRepository;
import com.elyon_yireh.surveys.security.services.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest, HttpServletResponse response) {
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, this.userDetailsService.loginUser(userRequest).token())
                .body(this.userDetailsService.loginUser(userRequest));
    }

    @GetMapping("/admin")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> admin(Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("READ"))) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> logout(HttpServletResponse response) {
        response.setHeader(HttpHeaders.SET_COOKIE, "access_token =; Max-Age=0; Path=/; HttpOnly; SameSite=None; Secure");
        return ResponseEntity.ok(true);
    }

}
