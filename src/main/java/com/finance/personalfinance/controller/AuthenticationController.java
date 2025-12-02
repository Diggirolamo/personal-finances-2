package com.finance.personalfinance.controller;

import com.finance.personalfinance.auth.AuthenticationRequest;
import com.finance.personalfinance.auth.AuthenticationResponse;
import com.finance.personalfinance.auth.RegisterRequest;
import com.finance.personalfinance.model.Role;
import com.finance.personalfinance.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/update")
    public ResponseEntity<AuthenticationResponse> update(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.update(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AuthenticationResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        return ResponseEntity.ok(service.refreshToken(refreshToken));
    }

    @PutMapping("/change-role/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> changeRole(@PathVariable Long userId, @RequestParam Role role) {
        service.changeUserRole(userId, role);

        return ResponseEntity.ok("Role update seccessfully");
    }
}