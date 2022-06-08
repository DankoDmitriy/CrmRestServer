package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.PhoneType;
import com.danko.crm.model.Status;
import com.danko.crm.service.converter.dto_to_entity.DtoToPhoneTypeConverter;
import com.danko.crm.service.dto.PhoneTypeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DtoToPhoneTypeConverterTest {
    private final static String NAME = "Name";
    private final static Long ID = 1L;

    private DtoToPhoneTypeConverter dtoToPhoneTypeConverter;
    private PhoneTypeDto phoneTypeDto;
    private PhoneType expected;

    @BeforeEach
    public void init() {
        dtoToPhoneTypeConverter = new DtoToPhoneTypeConverter();
        LocalDateTime localDateTime = LocalDateTime.now();
        phoneTypeDto = PhoneTypeDto.builder()
                .id(ID)
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
        expected = PhoneType.builder()
                .id(ID)
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Test
    void convert() {
        PhoneType actual = dtoToPhoneTypeConverter.convert(phoneTypeDto);
        assertEquals(expected, actual);
    }
}