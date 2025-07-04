package com.clone.metube.authentication.normal.controllers;

import com.clone.metube.authentication.normal.dtos.AccountDetailUpdateRequest;
import com.clone.metube.authentication.normal.dtos.AccountLoginRequest;
import com.clone.metube.authentication.normal.dtos.AccountRegisterRequest;
import com.clone.metube.authentication.normal.dtos.AccountUnregisterRequest;
import com.clone.metube.authentication.normal.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> userRequestRegister(@RequestBody AccountRegisterRequest registerRequest) {
        return ResponseEntity.ok("");
    }

    @PostMapping("/login")
    public ResponseEntity<?> userRequestLogin(@RequestBody AccountLoginRequest loginRequest) {
        return ResponseEntity.ok("");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> userRequestLogout(@RequestBody AccountLoginRequest logoutRequest) {
        return ResponseEntity.ok("");
    }

    @PatchMapping("/update")
    public ResponseEntity<?> userRequestDetailUpdate(@RequestBody AccountDetailUpdateRequest detailUpdateRequest) {
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/unregister")
    public ResponseEntity<?> userRequestUnregister(@RequestBody AccountUnregisterRequest unregisterRequest) {
        return ResponseEntity.ok("");
    }
}
