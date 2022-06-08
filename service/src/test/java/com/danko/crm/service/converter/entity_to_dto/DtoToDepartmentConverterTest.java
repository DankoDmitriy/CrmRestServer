package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.Department;
import com.danko.crm.model.Status;
import com.danko.crm.service.converter.dto_to_entity.DtoToDepartmentConverter;
import com.danko.crm.service.dto.DepartmentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DtoToDepartmentConverterTest {
    private final static String DEPARTMENT_NAME = "Department";
    private final static Long ID = 1L;

    private DtoToDepartmentConverter dtoToDepartmentConverter;
    private DepartmentDto departmentDto;
    private Department expected;

    @BeforeEach
    public void init() {
        dtoToDepartmentConverter = new DtoToDepartmentConverter();
        LocalDateTime localDateTime = LocalDateTime.now();
        departmentDto = DepartmentDto.builder()
                .id(ID)
                .name(DEPARTMENT_NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
        expected = Department.builder()
                .id(ID)
                .name(DEPARTMENT_NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Test
    void convert() {
        Department actual = dtoToDepartmentConverter.convert(departmentDto);
        assertEquals(expected, actual);
    }
}