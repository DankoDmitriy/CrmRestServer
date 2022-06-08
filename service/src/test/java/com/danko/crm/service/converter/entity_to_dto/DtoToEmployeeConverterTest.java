package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.Employee;
import com.danko.crm.model.Status;
import com.danko.crm.service.converter.dto_to_entity.DtoToEmployeeConverter;
import com.danko.crm.service.dto.EmployeeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DtoToEmployeeConverterTest {
    private final static String STRING_DATA = "data";
    private final static String EMAIL = "info@gmail.com";
    private final static Long ID = 1L;
    private final static Integer TG_ID = 12345678;

    private DtoToEmployeeConverter dtoToEmployeeConverter;
    private EmployeeDto employeeDto;
    private Employee expected;

    @BeforeEach
    public void init() {
        dtoToEmployeeConverter = new DtoToEmployeeConverter();
        LocalDateTime localDateTime = LocalDateTime.now();
        employeeDto = EmployeeDto.builder()
                .id(ID)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .firstName(STRING_DATA)
                .lastName(STRING_DATA)
                .patronymic(STRING_DATA)
                .birthday(localDateTime)
                .contractStart(localDateTime)
                .contractFinish(localDateTime)
                .userName(STRING_DATA)
                .email(EMAIL)
                .tgId(TG_ID)
                .comment(STRING_DATA)
                .build();
        expected = Employee.builder()
                .id(ID)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .firstName(STRING_DATA)
                .lastName(STRING_DATA)
                .patronymic(STRING_DATA)
                .birthday(localDateTime)
                .contractStart(localDateTime)
                .contractFinish(localDateTime)
                .userName(STRING_DATA)
                .email(EMAIL)
                .tgId(TG_ID)
                .comment(STRING_DATA)
                .build();
    }

    @Test
    void convert() {
        Employee actual = dtoToEmployeeConverter.convert(employeeDto);
        assertEquals(expected, actual);
    }
}