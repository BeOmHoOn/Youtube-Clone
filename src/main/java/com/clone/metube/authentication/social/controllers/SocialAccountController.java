package com.clone.metube.authentication.social.controllers;

import com.clone.metube.authentication.social.dtos.DetailUpdateRequest;
import com.clone.metube.authentication.social.dtos.LoginRequest;
import com.clone.metube.authentication.social.dtos.RegisterRequest;
import com.clone.metube.authentication.social.dtos.UnregisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/oauth")
public class SocialAccountController {
    @PostMapping("/register")
    public ResponseEntity<?> userRequestRegister(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok("");
    }

    @PostMapping("/login")
    public ResponseEntity<?> userRequestLogin(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok("");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> userRequestLogout(@RequestBody LoginRequest logoutRequest) {
        return ResponseEntity.ok("");
    }

    @PatchMapping("/update")
    public ResponseEntity<?> userRequestDetailUpdate(@RequestBody DetailUpdateRequest detailUpdateRequest) {
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/unregister")
    public ResponseEntity<?> userRequestUnregister(@RequestBody UnregisterRequest unregisterRequest) {
        return ResponseEntity.ok("");
    }
}
