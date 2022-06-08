package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.Role;
import com.danko.crm.service.dto.RoleDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoleToDtoConverter implements Converter<Role, RoleDto> {
    @Override
    public RoleDto convert(Role source) {
        return RoleDto.builder()
                .id(source.getId())
                .name(source.getName())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .build();
    }
}
