package com.danko.crm.data_provider.impl;

import com.danko.crm.data_provider.DataProviderDto;
import com.danko.crm.data_provider.DataProviderEntity;
import com.danko.crm.model.Ltd;
import com.danko.crm.model.LtdBank;
import com.danko.crm.model.LtdContract;
import com.danko.crm.model.Status;
import com.danko.crm.service.dto.LtdBankDto;
import com.danko.crm.service.dto.LtdContractDto;
import com.danko.crm.service.dto.LtdDto;

import java.time.LocalDateTime;
import java.util.Arrays;

public class LtdEntityAndLtdDtoProvider implements DataProviderDto<LtdDto>, DataProviderEntity<Ltd> {
    private final static Long ID = 1L;
    private final LocalDateTime localDateTime = LocalDateTime.now();

    private final static String FULL_NAME = "full name";
    private final static String SHORT_NAME = "short name";
    private final static String ADDRESS = "address";
    private final static String UNP = "unp";

    private final static String NUMBER = "number";
    private final static String OTHER = "other";

    private final static String REQUISITES = "requisites";

    @Override
    public LtdDto getDtoWithOutId() {
        return LtdDto.builder()
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

                .build();
    }

    @Override
    public LtdDto getDtoWithId() {
        return LtdDto.builder()
                .id(ID)
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

                .build();
    }

    @Override
    public LtdDto getDtoWithIdNotActive() {
        LtdDto ltdDto = getDtoWithId();
        ltdDto.setStatus(Status.NOT_ACTIVE);
        return ltdDto;
    }

    @Override
    public Ltd getEntityWithOutId() {
        return Ltd.builder()
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

                .build();
    }

    @Override
    public Ltd getEntityWithId() {
        return Ltd.builder()
                .id(ID)
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

                .build();
    }

    @Override
    public Ltd getEntityWithIdNotActive() {
        Ltd ltd = getEntityWithId();
        ltd.setStatus(Status.NOT_ACTIVE);
        return ltd;
    }
}
