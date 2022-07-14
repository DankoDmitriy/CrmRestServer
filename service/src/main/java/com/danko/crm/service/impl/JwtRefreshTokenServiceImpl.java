package com.danko.crm.service.impl;

import com.danko.crm.model.Employee;
import com.danko.crm.model.RefreshToken;
import com.danko.crm.model.Status;
import com.danko.crm.repository.RefreshTokenRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EmployeeService;
import com.danko.crm.service.JwtRefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JwtRefreshTokenServiceImpl implements JwtRefreshTokenService {
    private RefreshTokenRepository tokenRepository;
    private EmployeeService employeeService;
    private DtoToEntityConverterService dtoToEntityConverterService;

    @Override
    @Transactional
    public RefreshToken saveToken(String token, Long employeeId) {

        Employee employee = dtoToEntityConverterService.convert(employeeService.findById(employeeId));
        LocalDateTime now = LocalDateTime.now();
        RefreshToken refreshToken = RefreshToken.builder()
                .employee(employee)
                .token(token)
                .created(now)
                .update(now)
                .status(Status.ACTIVE)
                .build();
        return tokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByTokenAndStatus(String token, Status status) {
        return tokenRepository.findByTokenAndStatus(token, status);
    }

    @Override
    @Transactional
    public void disableEmployeeTokens(Long employeeId) {
        List<RefreshToken> refreshTokenList = tokenRepository.findAllByEmployee_IdAndStatus(employeeId, Status.ACTIVE);
        refreshTokenList.forEach(refreshToken -> updateTokenStatus(refreshToken, Status.NOT_ACTIVE));
    }

    @Override
    @Transactional
    public RefreshToken updateTokenStatus(RefreshToken refreshToken, Status status) {
        refreshToken.setStatus(status);
        refreshToken.setUpdate(LocalDateTime.now());
        return tokenRepository.save(refreshToken);
    }

    @Override
    @Transactional
    public RefreshToken disableRefreshTokenAfterGenerateNewRefreshToken(String refreshToken) {
        Optional<RefreshToken> optionalRefreshToken = findByTokenAndStatus(refreshToken, Status.ACTIVE);
        return updateTokenStatus(optionalRefreshToken.get(), Status.NOT_ACTIVE);
    }
}
