package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.Department;
import com.danko.crm.model.Status;
import com.danko.crm.service.converter.entity_to_dto.DepartmentToDtoConverter;
import com.danko.crm.service.dto.DepartmentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DepartmentToDtoConverterTest {
    private final static String DEPARTMENT_NAME = "Department";
    private final static Long ID = 1L;

    private DepartmentToDtoConverter departmentToDtoConverter;
    private Department department;
    private DepartmentDto expected;

    @BeforeEach
    public void init() {
        departmentToDtoConverter = new DepartmentToDtoConverter();
        LocalDateTime localDateTime = LocalDateTime.now();
        department = Department.builder()
                .id(ID)
                .name(DEPARTMENT_NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
        expected = DepartmentDto.builder()
                .id(ID)
                .name(DEPARTMENT_NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Test
    void convert() {
        DepartmentDto actual = departmentToDtoConverter.convert(department);
        assertEquals(expected, actual);
    }
}