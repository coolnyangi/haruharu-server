package com.haruharu.haruharu.auth.controller;

import com.haruharu.haruharu.auth.service.AuthService;
import com.haruharu.haruharu.common.auth.CurrentUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        String token = authService.login(req.email(), req.password());
        return new LoginResponse(token);
    }

    @PostMapping("/logout")
    public void logout(@CurrentUserId Long userId) {
        authService.logout(userId);
    }

    public record LoginRequest(String email, String password) {}
    public record LoginResponse(String accessToken) {}
}