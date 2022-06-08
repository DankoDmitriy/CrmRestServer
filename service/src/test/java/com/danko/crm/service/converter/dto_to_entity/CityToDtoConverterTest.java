package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.City;
import com.danko.crm.model.Status;
import com.danko.crm.service.converter.entity_to_dto.CityToDtoConverter;
import com.danko.crm.service.dto.CityDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CityToDtoConverterTest {
    private final static String CITY_NAME = "Minsk";
    private final static Long ID = 1L;

    private CityToDtoConverter cityToDtoConverter;
    private City city;
    private CityDto expected;

    @BeforeEach
    public void init() {
        cityToDtoConverter = new CityToDtoConverter();
        LocalDateTime localDateTime = LocalDateTime.now();
        city = City.builder()
                .id(ID)
                .name(CITY_NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
        expected = CityDto.builder()
                .id(ID)
                .name(CITY_NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Test
    void convert() {
        CityDto actual = cityToDtoConverter.convert(city);
        assertEquals(expected, actual);
    }
}