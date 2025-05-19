package com.example.backend.security.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.example.backend.security.dtos.AuthRequest;
import com.example.backend.security.dtos.AuthResponse;
import com.example.backend.security.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for authentication")
@CrossOrigin("*")
public class AuthController {
    private final AuthenticationService authService;

    @PostMapping("/register")
    @Operation(summary = "Register new user", description = "Creates a new user with CLIENT role")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request, "ROLE_CLIENT"));
    }

    @PostMapping("/register/employee")
    @Operation(summary = "Register employee", description = "Creates a new user with EMPLOYEE role")
    public ResponseEntity<AuthResponse> registerEmployee(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request, "ROLE_EMPLOYEE"));
    }

    @PostMapping("/register/admin")
    @Operation(summary = "Register admin", description = "Creates a new user with ADMIN role")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request, "ROLE_ADMIN"));
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Authenticates a user and returns JWT token")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
