package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.UserAuth;
import com.danko.crm.service.dto.UserAuthDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserAuthToDtoConverter implements Converter<UserAuth, UserAuthDto> {
    @Override
    public UserAuthDto convert(UserAuth source) {
        return UserAuthDto.builder()
                .id(source.getId())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .userName(source.getUserName())
                .password(source.getPassword())
                .email(source.getEmail())
                .build();
    }
}
