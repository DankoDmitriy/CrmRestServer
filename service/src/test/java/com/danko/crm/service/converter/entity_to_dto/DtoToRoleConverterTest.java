package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.Role;
import com.danko.crm.model.Status;
import com.danko.crm.service.converter.dto_to_entity.DtoToRoleConverter;
import com.danko.crm.service.dto.RoleDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DtoToRoleConverterTest {
    private final static String ROLE_NAME = "Role";
    private final static Long ID = 1L;

    private DtoToRoleConverter dtoToRoleConverter;
    private RoleDto roleDto;
    private Role expected;

    @BeforeEach
    public void init() {
        dtoToRoleConverter = new DtoToRoleConverter();
        LocalDateTime localDateTime = LocalDateTime.now();
        roleDto = RoleDto.builder()
                .id(ID)
                .name(ROLE_NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
        expected = Role.builder()
                .id(ID)
                .name(ROLE_NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Test
    void convert() {
        Role actual = dtoToRoleConverter.convert(roleDto);
        assertEquals(expected, actual);
    }
}
