package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.City;
import com.danko.crm.model.Status;
import com.danko.crm.service.converter.dto_to_entity.DtoToCityConverter;
import com.danko.crm.service.dto.CityDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DtoToCityConverterTest {
    private final static String CITY_NAME = "Minsk";
    private final static Long ID = 1L;

    private DtoToCityConverter dtoToCityConverter;
    private CityDto cityDto;
    private City expected;

    @BeforeEach
    public void init() {
        dtoToCityConverter = new DtoToCityConverter();
        LocalDateTime localDateTime = LocalDateTime.now();
        cityDto = CityDto.builder()
                .id(ID)
                .name(CITY_NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
        expected = City.builder()
                .id(ID)
                .name(CITY_NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Test
    void convert() {
        City actual = dtoToCityConverter.convert(cityDto);
        assertEquals(expected, actual);
    }
}