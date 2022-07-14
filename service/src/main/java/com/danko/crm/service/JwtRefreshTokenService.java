package com.danko.crm.service;

import com.danko.crm.model.RefreshToken;
import com.danko.crm.model.Status;

import java.util.Optional;

public interface JwtRefreshTokenService {
    RefreshToken saveToken(String token, Long employeeId);

    Optional<RefreshToken> findByTokenAndStatus(String token, Status status);

    void disableEmployeeTokens(Long employeeId);

    RefreshToken updateTokenStatus(RefreshToken refreshToken, Status status);

    RefreshToken disableRefreshTokenAfterGenerateNewRefreshToken(String refreshToken);
}
