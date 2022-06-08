package com.danko.crm.data_provider.impl;

import com.danko.crm.data_provider.DataProviderDto;
import com.danko.crm.data_provider.DataProviderEntity;
import com.danko.crm.model.Ltd;
import com.danko.crm.model.LtdContract;
import com.danko.crm.model.Status;
import com.danko.crm.service.dto.LtdContractDto;
import com.danko.crm.service.dto.LtdDto;

import java.time.LocalDateTime;

public class LtdContractEntityAndLtdContractDtoProvider implements DataProviderDto<LtdContractDto>, DataProviderEntity<LtdContract> {
    private final static Long ID = 1L;
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final static String NUMBER = "number";
    private final static String OTHER = "other";
    private final static String LTD_FULL_NAME = "ltd full name";

    @Override
    public LtdContractDto getDtoWithOutId() {
        return LtdContractDto.builder()
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .number(NUMBER)
                .other(OTHER)
                .contractStart(localDateTime)
                .ltd(LtdDto.builder()
                        .id(ID)
                        .nameFull(LTD_FULL_NAME)
                        .status(Status.ACTIVE)
                        .build())
                .build();
    }

    @Override
    public LtdContractDto getDtoWithId() {
        return LtdContractDto.builder()
                .id(ID)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .number(NUMBER)
                .other(OTHER)
                .contractStart(localDateTime)
                .ltd(LtdDto.builder()
                        .id(ID)
                        .nameFull(LTD_FULL_NAME)
                        .status(Status.ACTIVE)
                        .build())
                .build();
    }

    @Override
    public LtdContractDto getDtoWithIdNotActive() {
        LtdContractDto ltdContractDto = getDtoWithId();
        ltdContractDto.setStatus(Status.NOT_ACTIVE);
        return ltdContractDto;
    }

    @Override
    public LtdContract getEntityWithOutId() {
        return LtdContract.builder()
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .number(NUMBER)
                .other(OTHER)
                .contractStart(localDateTime)
                .ltd(Ltd.builder()
                        .id(ID)
                        .nameFull(LTD_FULL_NAME)
                        .status(Status.ACTIVE)
                        .build())
                .build();
    }

    @Override
    public LtdContract getEntityWithId() {
        return LtdContract.builder()
                .id(ID)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .number(NUMBER)
                .other(OTHER)
                .contractStart(localDateTime)
                .ltd(Ltd.builder()
                        .id(ID)
                        .nameFull(LTD_FULL_NAME)
                        .status(Status.ACTIVE)
                        .build())
                .build();
    }

    @Override
    public LtdContract getEntityWithIdNotActive() {
        LtdContract ltdContract = getEntityWithId();
        ltdContract.setStatus(Status.NOT_ACTIVE);
        return ltdContract;
    }
}
