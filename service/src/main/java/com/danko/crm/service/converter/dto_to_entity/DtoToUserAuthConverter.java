package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.UserAuth;
import com.danko.crm.service.dto.UserAuthDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DtoToUserAuthConverter implements Converter<UserAuthDto, UserAuth> {
    @Override
    public UserAuth convert(UserAuthDto source) {
        return UserAuth.builder()
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
