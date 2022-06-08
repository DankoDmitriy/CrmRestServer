package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.Position;
import com.danko.crm.model.Status;
import com.danko.crm.service.converter.dto_to_entity.DtoToPositionConverter;
import com.danko.crm.service.dto.PositionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DtoToPositionConverterTest {
    private final static String NAME = "Name";
    private final static String DESCRIPTION = "description";
    private final static Long ID = 1L;
    private final static Integer SUBORDINATION_LEVEL = 1;

    private DtoToPositionConverter dtoToPositionConverter;
    private PositionDto positionDto;
    private Position expected;

    @BeforeEach
    public void init() {
        dtoToPositionConverter = new DtoToPositionConverter();
        LocalDateTime localDateTime = LocalDateTime.now();
        positionDto = PositionDto.builder()
                .id(ID)
                .name(NAME)
                .description(DESCRIPTION)
                .subordinationLevel(SUBORDINATION_LEVEL)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
        expected = Position.builder()
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
        Position actual = dtoToPositionConverter.convert(positionDto);
        assertEquals(expected, actual);
    }
}