package com.rawlynrogers.rawlynrogerswebsite.controller;

import com.rawlynrogers.rawlynrogerswebsite.dto.LoginRequest;
import com.rawlynrogers.rawlynrogerswebsite.dto.LoginResponse;
import com.rawlynrogers.rawlynrogerswebsite.security.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {

        boolean usernameMatches = adminUsername.equals(loginRequest.getUsername());
        boolean passwordMatches = passwordEncoder.matches(
                loginRequest.getPassword(),
                passwordEncoder.encode(adminPassword)
        );

        if (!usernameMatches || !passwordMatches) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtService.generateToken(loginRequest.getUsername());

        return new LoginResponse(token, jwtService.getExpirationMs());
    }
}
