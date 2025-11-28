package com.finance.personalfinance.service;

import com.finance.personalfinance.model.RefreshToken;
import com.finance.personalfinance.repository.RefreshTokenRepository;
import com.finance.personalfinance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository repository;
    private final UserRepository userRepository;

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken token = RefreshToken.builder()
                .user(userRepository.findById(userId).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(new Date(System.currentTimeMillis() + 1000L * 60 * 60 *24 * 30))
                .build();

        return repository.save(token);
    }

    public RefreshToken verifyExpiration(String tokenStr) {
        RefreshToken token = repository.findByToken(tokenStr)
                .orElseThrow(() -> new RuntimeException("Refresh token not found!"));


        if (token.getExpiryDate().before(new Date())) {
            repository.delete(token);
            throw new RuntimeException("Refresh token expired!");
        }

        return token;
    }
}