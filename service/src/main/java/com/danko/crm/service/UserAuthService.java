package com.danko.crm.service;

import com.danko.crm.service.dto.UserAuthDto;

import java.util.Optional;

public interface UserAuthService {
    Optional<UserAuthDto> findUserByUserName(String userName);
}
