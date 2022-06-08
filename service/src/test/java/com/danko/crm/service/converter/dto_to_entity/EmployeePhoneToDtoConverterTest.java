package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.EmployeePhone;
import com.danko.crm.model.Status;
import com.danko.crm.service.converter.entity_to_dto.EmployeePhoneToDtoConverter;
import com.danko.crm.service.dto.EmployeePhoneDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeePhoneToDtoConverterTest {
    private final static String NAME = "Name";
    private final static Long ID = 1L;

    private EmployeePhoneToDtoConverter employeePhoneToDtoConverter;
    private EmployeePhone employeePhone;
    private EmployeePhoneDto expected;

    @BeforeEach
    public void init() {
        employeePhoneToDtoConverter = new EmployeePhoneToDtoConverter();
        LocalDateTime localDateTime = LocalDateTime.now();
        employeePhone = EmployeePhone.builder()
                .id(ID)
                .number(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
        expected = EmployeePhoneDto.builder()
                .id(ID)
                .number(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Test
    void convert() {
        EmployeePhoneDto actual = employeePhoneToDtoConverter.convert(employeePhone);
        assertEquals(expected, actual);
    }
}