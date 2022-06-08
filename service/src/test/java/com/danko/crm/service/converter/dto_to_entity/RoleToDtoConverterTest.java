package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.Role;
import com.danko.crm.model.Status;
import com.danko.crm.service.converter.entity_to_dto.RoleToDtoConverter;
import com.danko.crm.service.dto.RoleDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleToDtoConverterTest {
    private final static String ROLE_NAME = "Role";
    private final static Long ID = 1L;

    private RoleToDtoConverter roleToDtoConverter;
    private Role role;
    private RoleDto expected;

    @BeforeEach
    public void init() {
        roleToDtoConverter = new RoleToDtoConverter();
        LocalDateTime localDateTime = LocalDateTime.now();
        role = Role.builder()
                .id(ID)
                .name(ROLE_NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
        expected = RoleDto.builder()
                .id(ID)
                .name(ROLE_NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Test
    void convert() {
        RoleDto actual = roleToDtoConverter.convert(role);
        assertEquals(expected, actual);
    }
}