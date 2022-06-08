package com.danko.crm.data_provider.impl;

import com.danko.crm.data_provider.DataProviderDto;
import com.danko.crm.data_provider.DataProviderEntity;
import com.danko.crm.model.Department;
import com.danko.crm.model.Position;
import com.danko.crm.model.Status;
import com.danko.crm.service.dto.DepartmentDto;
import com.danko.crm.service.dto.PositionDto;

import java.time.LocalDateTime;
import java.util.Arrays;

public class DepartmentEntityAndDepartmentDtoProvider implements DataProviderDto<DepartmentDto>, DataProviderEntity<Department> {
    private final static Long ID = 1L;
    private final static String NAME = "Ticket Type";
    private final static String POSITION_NAME = "Position name";
    private final static String POSITION_DESCRIPTION = "Description";
    private final static Integer POSITION_SUBORDINATION_LEVEL = 1;
    private final LocalDateTime localDateTime = LocalDateTime.now();

    @Override
    public DepartmentDto getDtoWithOutId() {
        return DepartmentDto.builder()
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .positions(
                        Arrays.asList(
                                PositionDto.builder()
                                        .id(ID)
                                        .name(POSITION_NAME)
                                        .description(POSITION_DESCRIPTION)
                                        .subordinationLevel(POSITION_SUBORDINATION_LEVEL)
                                        .created(localDateTime)
                                        .update(localDateTime)
                                        .status(Status.ACTIVE)
                                        .build())
                )
                .build();
    }

    @Override
    public DepartmentDto getDtoWithId() {
        return DepartmentDto.builder()
                .id(ID)
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .positions(
                        Arrays.asList(
                                PositionDto.builder()
                                        .id(ID)
                                        .name(POSITION_NAME)
                                        .description(POSITION_DESCRIPTION)
                                        .subordinationLevel(POSITION_SUBORDINATION_LEVEL)
                                        .created(localDateTime)
                                        .update(localDateTime)
                                        .status(Status.ACTIVE)
                                        .build())
                )
                .build();
    }

    @Override
    public DepartmentDto getDtoWithIdNotActive() {
        DepartmentDto departmentDto = getDtoWithId();
        departmentDto.setStatus(Status.NOT_ACTIVE);
        return departmentDto;
    }

    @Override
    public Department getEntityWithOutId() {
        return Department.builder()
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .positions(
                        Arrays.asList(
                                Position.builder()
                                        .id(ID)
                                        .name(POSITION_NAME)
                                        .description(POSITION_DESCRIPTION)
                                        .subordinationLevel(POSITION_SUBORDINATION_LEVEL)
                                        .created(localDateTime)
                                        .update(localDateTime)
                                        .status(Status.ACTIVE)
                                        .build())
                )
                .build();
    }

    @Override
    public Department getEntityWithId() {
        return Department.builder()
                .id(ID)
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .positions(
                        Arrays.asList(
                                Position.builder()
                                        .id(ID)
                                        .name(POSITION_NAME)
                                        .description(POSITION_DESCRIPTION)
                                        .subordinationLevel(POSITION_SUBORDINATION_LEVEL)
                                        .created(localDateTime)
                                        .update(localDateTime)
                                        .status(Status.ACTIVE)
                                        .build())
                )
                .build();
    }

    @Override
    public Department getEntityWithIdNotActive() {
        Department department = getEntityWithId();
        department.setStatus(Status.NOT_ACTIVE);
        return department;
    }
}
