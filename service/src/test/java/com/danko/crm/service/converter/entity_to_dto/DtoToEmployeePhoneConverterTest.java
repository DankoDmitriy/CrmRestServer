package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.EmployeePhone;
import com.danko.crm.model.Status;
import com.danko.crm.service.converter.dto_to_entity.DtoToEmployeePhoneConverter;
import com.danko.crm.service.dto.EmployeePhoneDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DtoToEmployeePhoneConverterTest {
    private final static String NAME = "Name";
    private final static Long ID = 1L;

    private DtoToEmployeePhoneConverter dtoToEmployeePhoneConverter;
    private EmployeePhoneDto employeePhoneDto;
    private EmployeePhone expected;

    @BeforeEach
    public void init() {
        dtoToEmployeePhoneConverter = new DtoToEmployeePhoneConverter();
        LocalDateTime localDateTime = LocalDateTime.now();
        employeePhoneDto = EmployeePhoneDto.builder()
                .id(ID)
                .number(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
        expected = EmployeePhone.builder()
                .id(ID)
                .number(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Test
    void convert() {
        EmployeePhone actual = dtoToEmployeePhoneConverter.convert(employeePhoneDto);
        assertEquals(expected, actual);
    }
}