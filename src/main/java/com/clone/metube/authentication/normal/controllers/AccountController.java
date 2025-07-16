package com.clone.metube.authentication.normal.controllers;

import com.clone.metube.authentication.normal.dtos.AccountDetailUpdateRequest;
import com.clone.metube.authentication.normal.dtos.AccountLoginRequest;
import com.clone.metube.authentication.normal.dtos.AccountRegisterRequest;
import com.clone.metube.authentication.normal.dtos.AccountUnregisterRequest;
import com.clone.metube.authentication.normal.repositories.AccountRepository;
import com.clone.metube.authentication.normal.services.AccountService;
import com.clone.metube.global.annotations.RequireAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AccountRegisterRequest registerRequest) {
        return ResponseEntity.ok(accountService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountLoginRequest loginRequest) {
        return ResponseEntity.ok(accountService.login(loginRequest));
    }

    @RequireAccessToken
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody AccountLoginRequest logoutRequest) {
        return ResponseEntity.ok("");
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody AccountDetailUpdateRequest detailUpdateRequest) {
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/unregister")
    public ResponseEntity<?> unregister(@RequestBody AccountUnregisterRequest unregisterRequest) {
        return ResponseEntity.ok("");
    }
}
