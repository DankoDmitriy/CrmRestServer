package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.PhoneType;
import com.danko.crm.model.Status;
import com.danko.crm.service.converter.entity_to_dto.PhoneTypeToDtoConverter;
import com.danko.crm.service.dto.PhoneTypeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhoneTypeToDtoConverterTest {
    private final static String NAME = "Name";
    private final static Long ID = 1L;

    private PhoneTypeToDtoConverter phoneTypeToDtoConverter;
    private PhoneType phoneType;
    private PhoneTypeDto expected;

    @BeforeEach
    public void init() {
        phoneTypeToDtoConverter = new PhoneTypeToDtoConverter();
        LocalDateTime localDateTime = LocalDateTime.now();
        phoneType = PhoneType.builder()
                .id(ID)
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
        expected = PhoneTypeDto.builder()
                .id(ID)
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Test
    void convert() {
        PhoneTypeDto actual = phoneTypeToDtoConverter.convert(phoneType);
        assertEquals(expected, actual);
    }
}