package com.finance.personalfinance.service;

import com.finance.personalfinance.auth.AuthenticationRequest;
import com.finance.personalfinance.auth.AuthenticationResponse;
import com.finance.personalfinance.auth.RegisterRequest;
import com.finance.personalfinance.model.RefreshToken;
import com.finance.personalfinance.model.Role;
import com.finance.personalfinance.model.User;
import com.finance.personalfinance.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() != null ? request.getRole() : Role.USER)
                .build();

        repository.save(user);
        String jwtToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

        return AuthenticationResponse.builder().
                token(jwtToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        String jwtToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

        return AuthenticationResponse.builder()
                .token(jwtToken).refreshToken(refreshToken.getToken())
                .build();
    }

    public AuthenticationResponse update(RegisterRequest request) {
        User user = repository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (request.getFirstname() != null) user.setFirstname(request.getFirstname());
        if (request.getLastname() != null) user.setLastname(request.getLastname());
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getPassword() != null) user.setPassword(passwordEncoder.encode(request.getPassword()));

        repository.save(user);

        String jwtToken = jwtService.generateToken(user);
        RefreshToken token = refreshTokenService.createRefreshToken(user.getId());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(token.getToken())
                .build();
    }

    @Transactional
    public AuthenticationResponse delete(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        refreshTokenService.deleteAllByUserId(id);
        repository.delete(user);

        return AuthenticationResponse.builder()
                .token("User deleted successfully")
                .build();
    }

    public AuthenticationResponse refreshToken(String refreshTokenStr) {
        RefreshToken refreshToken = refreshTokenService.verifyExpiration(refreshTokenStr);
        User user = refreshToken.getUser();
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshTokenStr)
                .build();
    }

    public User changeUserRole(Long userId, Role role) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setRole(role);

        return repository.save(user);
    }
}