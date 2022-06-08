package com.danko.crm.data_provider.impl;

import com.danko.crm.data_provider.DataProviderDto;
import com.danko.crm.data_provider.DataProviderEntity;
import com.danko.crm.model.City;
import com.danko.crm.model.Department;
import com.danko.crm.model.Employee;
import com.danko.crm.model.Position;
import com.danko.crm.model.Status;
import com.danko.crm.service.dto.CityDto;
import com.danko.crm.service.dto.DepartmentDto;
import com.danko.crm.service.dto.EmployeeDto;
import com.danko.crm.service.dto.PositionDto;

import java.time.LocalDateTime;

public class EmployeeEntityAndEmployeeDtoProvider implements DataProviderDto<EmployeeDto>, DataProviderEntity<Employee> {
    private final static Long ID = 1L;
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final static String FIRST_NAME = "First name";
    private final static String LAST_NAME = "Last Name";
    private final static String PATRONYMIC = "Patronymic";
    private final LocalDateTime BIRTHDAY = LocalDateTime.now();
    private final LocalDateTime CONTRACT_START = LocalDateTime.now();
    private final LocalDateTime CONTRACT_FINISH = LocalDateTime.now();
    private final static String USER_NAME = "username";
    private final static String EMAIL = "email@gmail.com";
    private final static String DOV = "dov123";
    private final static Integer TG_ID = 1235689;
    private final static String COMMENT = "comment";

    private final static String DEPARTMENT_NAME = "department name";
    private final static String POSITION_NAME = "Position name";
    private final static String POSITION_DESCRIPTION = "Description";
    private final static Integer POSITION_SUBORDINATION_LEVEL = 1;

    private final static String CITY_NAME = "city name";

    @Override
    public EmployeeDto getDtoWithOutId() {
        return EmployeeDto.builder()
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .patronymic(PATRONYMIC)
                .birthday(BIRTHDAY)
                .contractFinish(CONTRACT_FINISH)
                .contractStart(CONTRACT_START)
                .userName(USER_NAME)
                .email(EMAIL)
                .dov(DOV)
                .tgId(TG_ID)
                .comment(COMMENT)
                .position(PositionDto.builder()
                        .id(ID)
                        .name(POSITION_NAME)
                        .description(POSITION_DESCRIPTION)
                        .subordinationLevel(POSITION_SUBORDINATION_LEVEL)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .department(
                                DepartmentDto.builder()
                                        .id(ID)
                                        .name(DEPARTMENT_NAME)
                                        .created(localDateTime)
                                        .update(localDateTime)
                                        .status(Status.ACTIVE)
                                        .build()
                        )
                        .build())
                .department(DepartmentDto.builder()
                        .id(ID)
                        .name(DEPARTMENT_NAME)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .build())
                .city(CityDto.builder()
                        .id(ID)
                        .name(CITY_NAME)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .build())
                .build();
    }

    @Override
    public EmployeeDto getDtoWithId() {
        return EmployeeDto.builder()
                .id(ID)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .patronymic(PATRONYMIC)
                .birthday(BIRTHDAY)
                .contractFinish(CONTRACT_FINISH)
                .contractStart(CONTRACT_START)
                .userName(USER_NAME)
                .email(EMAIL)
                .dov(DOV)
                .tgId(TG_ID)
                .comment(COMMENT)
                .position(PositionDto.builder()
                        .id(ID)
                        .name(POSITION_NAME)
                        .description(POSITION_DESCRIPTION)
                        .subordinationLevel(POSITION_SUBORDINATION_LEVEL)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .department(
                                DepartmentDto.builder()
                                        .id(ID)
                                        .name(DEPARTMENT_NAME)
                                        .created(localDateTime)
                                        .update(localDateTime)
                                        .status(Status.ACTIVE)
                                        .build()
                        )
                        .build())
                .department(DepartmentDto.builder()
                        .id(ID)
                        .name(DEPARTMENT_NAME)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .build())
                .city(CityDto.builder()
                        .id(ID)
                        .name(CITY_NAME)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .build())
                .build();
    }

    @Override
    public EmployeeDto getDtoWithIdNotActive() {
        EmployeeDto employeeDto = getDtoWithId();
        employeeDto.setStatus(Status.NOT_ACTIVE);
        return employeeDto;
    }

    @Override
    public Employee getEntityWithOutId() {
        return Employee.builder()
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .patronymic(PATRONYMIC)
                .birthday(BIRTHDAY)
                .contractFinish(CONTRACT_FINISH)
                .contractStart(CONTRACT_START)
                .userName(USER_NAME)
                .email(EMAIL)
                .dov(DOV)
                .tgId(TG_ID)
                .comment(COMMENT)
                .position(Position.builder()
                        .id(ID)
                        .name(POSITION_NAME)
                        .description(POSITION_DESCRIPTION)
                        .subordinationLevel(POSITION_SUBORDINATION_LEVEL)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .department(
                                Department.builder()
                                        .id(ID)
                                        .name(DEPARTMENT_NAME)
                                        .created(localDateTime)
                                        .update(localDateTime)
                                        .status(Status.ACTIVE)
                                        .build()
                        )
                        .build())
                .department(Department.builder()
                        .id(ID)
                        .name(DEPARTMENT_NAME)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .build())
                .city(City.builder()
                        .id(ID)
                        .name(CITY_NAME)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .build())
                .build();
    }

    @Override
    public Employee getEntityWithId() {
        return Employee.builder()
                .id(ID)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .patronymic(PATRONYMIC)
                .birthday(BIRTHDAY)
                .contractFinish(CONTRACT_FINISH)
                .contractStart(CONTRACT_START)
                .userName(USER_NAME)
                .email(EMAIL)
                .dov(DOV)
                .tgId(TG_ID)
                .comment(COMMENT)
                .position(Position.builder()
                        .id(ID)
                        .name(POSITION_NAME)
                        .description(POSITION_DESCRIPTION)
                        .subordinationLevel(POSITION_SUBORDINATION_LEVEL)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .department(
                                Department.builder()
                                        .id(ID)
                                        .name(DEPARTMENT_NAME)
                                        .created(localDateTime)
                                        .update(localDateTime)
                                        .status(Status.ACTIVE)
                                        .build()
                        )
                        .build())
                .department(Department.builder()
                        .id(ID)
                        .name(DEPARTMENT_NAME)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .build())
                .city(City.builder()
                        .id(ID)
                        .name(CITY_NAME)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .build())
                .build();
    }

    @Override
    public Employee getEntityWithIdNotActive() {
        Employee employee = getEntityWithId();
        employee.setStatus(Status.NOT_ACTIVE);
        return employee;
    }
}
