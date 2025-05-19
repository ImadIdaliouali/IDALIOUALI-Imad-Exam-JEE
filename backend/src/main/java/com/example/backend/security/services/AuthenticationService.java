package com.example.backend.security.services;

import lombok.RequiredArgsConstructor;
import com.example.backend.security.dtos.AuthRequest;
import com.example.backend.security.dtos.AuthResponse;
import com.example.backend.security.entities.AppRole;
import com.example.backend.security.entities.AppUser;
import com.example.backend.security.repositories.AppRoleRepository;
import com.example.backend.security.repositories.AppUserRepository;
import com.example.backend.security.utils.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {
    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(AuthRequest request, String roleName) {
        var role = roleRepository.findByRoleName(roleName)
                .orElseGet(() -> roleRepository.save(AppRole.builder()
                        .roleName(roleName)
                        .build()));

        var roles = new ArrayList<AppRole>();
        roles.add(role);

        var user = AppUser.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getUsername()) // Default, can be updated later
                .roles(roles)
                .build();

        userRepository.save(user);

        var jwtToken = jwtTokenUtil.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        user.getRoles().stream()
                                .map(r -> new org.springframework.security.core.authority.SimpleGrantedAuthority(r.getRoleName()))
                                .collect(Collectors.toList())
                )
        );

        return AuthResponse.builder()
                .accessToken(jwtToken)
                .username(user.getUsername())
                .fullName(user.getFullName())
                .roles(user.getRoles().stream().map(AppRole::getRoleName).collect(Collectors.toList()))
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var jwtToken = jwtTokenUtil.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        user.getRoles().stream()
                                .map(r -> new org.springframework.security.core.authority.SimpleGrantedAuthority(r.getRoleName()))
                                .collect(Collectors.toList())
                )
        );

        return AuthResponse.builder()
                .accessToken(jwtToken)
                .username(user.getUsername())
                .fullName(user.getFullName())
                .roles(user.getRoles().stream().map(AppRole::getRoleName).collect(Collectors.toList()))
                .build();
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public void addRoleToUser(String username, String roleName) {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AppRole role = roleRepository.findByRoleName(roleName)
                .orElseGet(() -> roleRepository.save(AppRole.builder()
                        .roleName(roleName)
                        .build()));

        user.getRoles().add(role);
        userRepository.save(user);
    }
}
