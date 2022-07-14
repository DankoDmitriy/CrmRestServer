package com.danko.crm.repository;

import com.danko.crm.model.RefreshToken;
import com.danko.crm.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByTokenAndStatus(String token, Status status);

    List<RefreshToken> findAllByEmployee_IdAndStatus(Long id, Status status);
}
