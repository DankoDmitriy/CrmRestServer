package com.danko.crm.service.impl;

import com.danko.crm.model.UserAuth;
import com.danko.crm.repository.UserAuthRepository;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.UserAuthService;
import com.danko.crm.service.dto.UserAuthDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {
    private UserAuthRepository userAuthRepository;
    private EntityToDtoConverterService entityToDtoConverterService;

    @Override
    public Optional<UserAuthDto> findUserByUserName(String userName) {
        Optional<UserAuth> userAuthOptional = userAuthRepository.findByUserName(userName);
        if (userAuthOptional.isPresent()) {
            return Optional.of(entityToDtoConverterService.convert(userAuthOptional.get()));
        } else {
            return Optional.empty();
        }
    }
}
