package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.Position;
import com.danko.crm.model.Status;
import com.danko.crm.service.converter.entity_to_dto.PositionToDtoConverter;
import com.danko.crm.service.dto.PositionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionToDtoConverterTest {
    private final static String NAME = "Name";
    private final static String DESCRIPTION = "description";
    private final static Long ID = 1L;
    private final static Integer SUBORDINATION_LEVEL = 1;

    private PositionToDtoConverter positionToDtoConverter;
    private Position position;
    private PositionDto expected;

    @BeforeEach
    public void init() {
        positionToDtoConverter = new PositionToDtoConverter();
        LocalDateTime localDateTime = LocalDateTime.now();
        position = Position.builder()
                .id(ID)
                .name(NAME)
                .description(DESCRIPTION)
                .subordinationLevel(SUBORDINATION_LEVEL)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
        expected = PositionDto.builder()
                .id(ID)
                .name(NAME)
                .description(DESCRIPTION)
                .subordinationLevel(SUBORDINATION_LEVEL)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Test
    void convert() {
        PositionDto actual = positionToDtoConverter.convert(position);
        assertEquals(expected, actual);
    }
}