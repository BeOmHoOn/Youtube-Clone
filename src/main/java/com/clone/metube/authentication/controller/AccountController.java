package com.clone.metube.authentication.controller;

import com.clone.metube.authentication.dto.LoginRequest;
import com.clone.metube.authentication.dto.RegisterRequest;
import com.clone.metube.authentication.services.AccountService;
import com.clone.metube.global.annotations.RequireAccessToken;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest) {
        return accountService.register(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return accountService.login(loginRequest);
    }

    @RequireAccessToken
    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @RequestHeader(value = "Authorization", required = false) String accessToken,
            HttpServletResponse response) {
        return accountService.logout(accessToken, response);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update() {
        return ResponseEntity.ok("");
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@CookieValue(name = "Refresh-Token", required = false) String refreshToken) {
        return accountService.refreshingAccessToken(refreshToken);
    }

    @DeleteMapping("/disable")
    public ResponseEntity<?> unregister() {
        return ResponseEntity.ok("");
    }
}
