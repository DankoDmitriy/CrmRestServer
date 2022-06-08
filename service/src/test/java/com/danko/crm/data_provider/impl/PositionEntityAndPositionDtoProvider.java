package com.danko.crm.data_provider.impl;

import com.danko.crm.data_provider.DataProviderDto;
import com.danko.crm.data_provider.DataProviderEntity;
import com.danko.crm.model.Department;
import com.danko.crm.model.Position;
import com.danko.crm.model.Status;
import com.danko.crm.service.dto.DepartmentDto;
import com.danko.crm.service.dto.PositionDto;

import java.time.LocalDateTime;

public class PositionEntityAndPositionDtoProvider implements DataProviderDto<PositionDto>, DataProviderEntity<Position> {
    private final static Long ID = 1L;
    private final static String NAME = "department name";
    private final static String POSITION_NAME = "Position name";
    private final static String POSITION_DESCRIPTION = "Description";
    private final static Integer POSITION_SUBORDINATION_LEVEL = 1;
    private final LocalDateTime localDateTime = LocalDateTime.now();

    @Override
    public PositionDto getDtoWithOutId() {
        return PositionDto.builder()
                .name(POSITION_NAME)
                .description(POSITION_DESCRIPTION)
                .subordinationLevel(POSITION_SUBORDINATION_LEVEL)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .department(DepartmentDto.builder()
                        .id(ID)
                        .name(NAME)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .build())
                .build();
    }

    @Override
    public PositionDto getDtoWithId() {
        return PositionDto.builder()
                .id(ID)
                .name(POSITION_NAME)
                .description(POSITION_DESCRIPTION)
                .subordinationLevel(POSITION_SUBORDINATION_LEVEL)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .department(DepartmentDto.builder()
                        .id(ID)
                        .name(NAME)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .build())
                .build();
    }

    @Override
    public PositionDto getDtoWithIdNotActive() {
        PositionDto positionDto = getDtoWithId();
        positionDto.setStatus(Status.NOT_ACTIVE);
        return positionDto;
    }

    @Override
    public Position getEntityWithOutId() {
        return Position.builder()
                .name(POSITION_NAME)
                .description(POSITION_DESCRIPTION)
                .subordinationLevel(POSITION_SUBORDINATION_LEVEL)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .department(Department.builder()
                        .id(ID)
                        .name(NAME)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .build())
                .build();
    }

    @Override
    public Position getEntityWithId() {
        return Position.builder()
                .id(ID)
                .name(POSITION_NAME)
                .description(POSITION_DESCRIPTION)
                .subordinationLevel(POSITION_SUBORDINATION_LEVEL)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .department(Department.builder()
                        .id(ID)
                        .name(NAME)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .build())
                .build();
    }

    @Override
    public Position getEntityWithIdNotActive() {
        Position position = getEntityWithId();
        position.setStatus(Status.NOT_ACTIVE);
        return position;
    }
}
