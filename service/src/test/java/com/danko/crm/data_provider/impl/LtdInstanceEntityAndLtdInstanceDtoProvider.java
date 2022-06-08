package com.danko.crm.data_provider.impl;

import com.danko.crm.data_provider.DataProviderDto;
import com.danko.crm.data_provider.DataProviderEntity;
import com.danko.crm.model.City;
import com.danko.crm.model.Department;
import com.danko.crm.model.Employee;
import com.danko.crm.model.Ltd;
import com.danko.crm.model.LtdBank;
import com.danko.crm.model.LtdContract;
import com.danko.crm.model.LtdInstance;
import com.danko.crm.model.Position;
import com.danko.crm.model.Status;
import com.danko.crm.service.dto.CityDto;
import com.danko.crm.service.dto.DepartmentDto;
import com.danko.crm.service.dto.EmployeeDto;
import com.danko.crm.service.dto.LtdBankDto;
import com.danko.crm.service.dto.LtdContractDto;
import com.danko.crm.service.dto.LtdDto;
import com.danko.crm.service.dto.LtdInstanceDto;
import com.danko.crm.service.dto.PositionDto;

import java.time.LocalDateTime;
import java.util.Arrays;

public class LtdInstanceEntityAndLtdInstanceDtoProvider implements DataProviderDto<LtdInstanceDto>, DataProviderEntity<LtdInstance> {
    private final static Long ID = 1L;
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final static Integer SERVER = 1;
    private final static Integer UPS = 1;
    private final static Integer SWITCHS = 1;
    private final static Integer WORKPLACE = 1;
    private final static Integer EQUIPMENT = 1;
    private final static String OTHER = "other";
    private final static String CITY_NAME = "city name";
    private final static String NUMBER = "1111 AA-7";
    private final static String FULL_NAME = "full name";
    private final static String SHORT_NAME = "short name";
    private final static String ADDRESS = "address";
    private final static String UNP = "unp";
    private final static String REQUISITES = "requisites";

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


    @Override
    public LtdInstanceDto getDtoWithOutId() {
        return LtdInstanceDto.builder()
                .created(localDateTime)
                .update(localDateTime)
                .equipment(EQUIPMENT)
                .workplace(WORKPLACE)
                .switchs(SWITCHS)
                .server(SERVER)
                .ups(UPS)
                .telecomCabinet(1)
                .others(OTHER)
                .distanceMainOffice(100)
                .distanceLocalOffice(100)
                .type(1)
                .address("Address")
                .city(CityDto.builder()
                        .id(ID)
                        .name(CITY_NAME)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .build())
                .ltd(
                        LtdDto.builder()
                                .created(localDateTime)
                                .update(localDateTime)
                                .status(Status.ACTIVE)
                                .nameFull(FULL_NAME)
                                .nameShort(SHORT_NAME)
                                .address(ADDRESS)
                                .unp(UNP)
                                .ltdContracts(
                                        Arrays.asList(
                                                LtdContractDto.builder()
                                                        .id(ID)
                                                        .created(localDateTime)
                                                        .update(localDateTime)
                                                        .status(Status.ACTIVE)
                                                        .contractStart(localDateTime)
                                                        .other(OTHER)
                                                        .number(NUMBER)
                                                        .build()
                                        )
                                )
                                .ltdBanks(
                                        Arrays.asList(
                                                LtdBankDto.builder()
                                                        .id(ID)
                                                        .created(localDateTime)
                                                        .update(localDateTime)
                                                        .status(Status.ACTIVE)
                                                        .requisites(REQUISITES)
                                                        .build()
                                        )
                                )

                                .build()
                )
                .employee(
                        EmployeeDto.builder()
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
                                .build()
                )
                .build();
    }

    @Override
    public LtdInstanceDto getDtoWithId() {
        return LtdInstanceDto.builder()
                .id(ID)
                .created(localDateTime)
                .update(localDateTime)
                .equipment(EQUIPMENT)
                .workplace(WORKPLACE)
                .switchs(SWITCHS)
                .server(SERVER)
                .ups(UPS)
                .telecomCabinet(1)
                .others(OTHER)
                .distanceMainOffice(100)
                .distanceLocalOffice(100)
                .type(1)
                .address("Address")
                .city(CityDto.builder()
                        .id(ID)
                        .name(CITY_NAME)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .build())
                .ltd(
                        LtdDto.builder()
                                .created(localDateTime)
                                .update(localDateTime)
                                .status(Status.ACTIVE)
                                .nameFull(FULL_NAME)
                                .nameShort(SHORT_NAME)
                                .address(ADDRESS)
                                .unp(UNP)
                                .ltdContracts(
                                        Arrays.asList(
                                                LtdContractDto.builder()
                                                        .id(ID)
                                                        .created(localDateTime)
                                                        .update(localDateTime)
                                                        .status(Status.ACTIVE)
                                                        .contractStart(localDateTime)
                                                        .other(OTHER)
                                                        .number(NUMBER)
                                                        .build()
                                        )
                                )
                                .ltdBanks(
                                        Arrays.asList(
                                                LtdBankDto.builder()
                                                        .id(ID)
                                                        .created(localDateTime)
                                                        .update(localDateTime)
                                                        .status(Status.ACTIVE)
                                                        .requisites(REQUISITES)
                                                        .build()
                                        )
                                )

                                .build()
                )
                .employee(
                        EmployeeDto.builder()
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
                                .build()
                )
                .build();
    }

    @Override
    public LtdInstanceDto getDtoWithIdNotActive() {
        LtdInstanceDto ltdInstanceDto = getDtoWithId();
        ltdInstanceDto.setStatus(Status.NOT_ACTIVE);
        return ltdInstanceDto;
    }

    @Override
    public LtdInstance getEntityWithOutId() {
        return LtdInstance.builder()
                .created(localDateTime)
                .update(localDateTime)
                .equipment(EQUIPMENT)
                .workplace(WORKPLACE)
                .switchs(SWITCHS)
                .server(SERVER)
                .ups(UPS)
                .telecomCabinet(1)
                .others(OTHER)
                .distanceMainOffice(100)
                .distanceLocalOffice(100)
                .type(1)
                .address("Address")
                .city(City.builder()
                        .id(ID)
                        .name(CITY_NAME)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .build())
                .ltd(
                        Ltd.builder()
                                .created(localDateTime)
                                .update(localDateTime)
                                .status(Status.ACTIVE)
                                .nameFull(FULL_NAME)
                                .nameShort(SHORT_NAME)
                                .address(ADDRESS)
                                .unp(UNP)
                                .ltdContracts(
                                        Arrays.asList(
                                                LtdContract.builder()
                                                        .id(ID)
                                                        .created(localDateTime)
                                                        .update(localDateTime)
                                                        .status(Status.ACTIVE)
                                                        .contractStart(localDateTime)
                                                        .other(OTHER)
                                                        .number(NUMBER)
                                                        .build()
                                        )
                                )
                                .ltdBanks(
                                        Arrays.asList(
                                                LtdBank.builder()
                                                        .id(ID)
                                                        .created(localDateTime)
                                                        .update(localDateTime)
                                                        .status(Status.ACTIVE)
                                                        .requisites(REQUISITES)
                                                        .build()
                                        )
                                )

                                .build()
                )
                .employee(
                        Employee.builder()
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
                                .build()
                )
                .build();
    }

    @Override
    public LtdInstance getEntityWithId() {
        return LtdInstance.builder()
                .id(ID)
                .created(localDateTime)
                .update(localDateTime)
                .equipment(EQUIPMENT)
                .workplace(WORKPLACE)
                .switchs(SWITCHS)
                .server(SERVER)
                .ups(UPS)
                .telecomCabinet(1)
                .others(OTHER)
                .distanceMainOffice(100)
                .distanceLocalOffice(100)
                .type(1)
                .address("Address")
                .city(City.builder()
                        .id(ID)
                        .name(CITY_NAME)
                        .created(localDateTime)
                        .update(localDateTime)
                        .status(Status.ACTIVE)
                        .build())
                .ltd(
                        Ltd.builder()
                                .created(localDateTime)
                                .update(localDateTime)
                                .status(Status.ACTIVE)
                                .nameFull(FULL_NAME)
                                .nameShort(SHORT_NAME)
                                .address(ADDRESS)
                                .unp(UNP)
                                .ltdContracts(
                                        Arrays.asList(
                                                LtdContract.builder()
                                                        .id(ID)
                                                        .created(localDateTime)
                                                        .update(localDateTime)
                                                        .status(Status.ACTIVE)
                                                        .contractStart(localDateTime)
                                                        .other(OTHER)
                                                        .number(NUMBER)
                                                        .build()
                                        )
                                )
                                .ltdBanks(
                                        Arrays.asList(
                                                LtdBank.builder()
                                                        .id(ID)
                                                        .created(localDateTime)
                                                        .update(localDateTime)
                                                        .status(Status.ACTIVE)
                                                        .requisites(REQUISITES)
                                                        .build()
                                        )
                                )

                                .build()
                )
                .employee(
                        Employee.builder()
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
                                .build()
                )
                .build();
    }

    @Override
    public LtdInstance getEntityWithIdNotActive() {
        LtdInstance ltdInstance = getEntityWithId();
        ltdInstance.setStatus(Status.NOT_ACTIVE);
        return ltdInstance;
    }
}
