package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.Role;
import com.danko.crm.service.dto.RoleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToRoleConverter implements Converter<RoleDto, Role> {
    @Override
    public Role convert(RoleDto source) {
        return Role.builder()
                .id(source.getId())
                .name(source.getName())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .build();
    }
}
