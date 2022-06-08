package com.danko.crm.data_provider.impl;

import com.danko.crm.data_provider.DataProviderDto;
import com.danko.crm.data_provider.DataProviderEntity;
import com.danko.crm.model.Ltd;
import com.danko.crm.model.LtdBank;
import com.danko.crm.model.Status;
import com.danko.crm.service.dto.LtdBankDto;
import com.danko.crm.service.dto.LtdDto;

import java.time.LocalDateTime;

public class LtdBankEntityAndLtdBankDtoProvider implements DataProviderDto<LtdBankDto>, DataProviderEntity<LtdBank> {
    private final static Long ID = 1L;
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final static String REQUISITES = "requisites";
    private final static String LTD_FULL_NAME = "ltd full name";

    @Override
    public LtdBankDto getDtoWithOutId() {
        return LtdBankDto.builder()
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .requisites(REQUISITES)
                .ltd(LtdDto.builder()
                        .id(ID)
                        .nameFull(LTD_FULL_NAME)
                        .status(Status.ACTIVE)
                        .created(localDateTime)
                        .update(localDateTime)
                        .build())
                .build();
    }

    @Override
    public LtdBankDto getDtoWithId() {
        return LtdBankDto.builder()
                .id(ID)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .requisites(REQUISITES)
                .ltd(LtdDto.builder()
                        .id(ID)
                        .nameFull(LTD_FULL_NAME)
                        .status(Status.ACTIVE)
                        .created(localDateTime)
                        .update(localDateTime)
                        .build())
                .build();
    }

    @Override
    public LtdBankDto getDtoWithIdNotActive() {
        LtdBankDto ltdBankDto = getDtoWithId();
        ltdBankDto.setStatus(Status.NOT_ACTIVE);
        return ltdBankDto;
    }

    @Override
    public LtdBank getEntityWithOutId() {
        return LtdBank.builder()
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .requisites(REQUISITES)
                .ltd(Ltd.builder()
                        .id(ID)
                        .nameFull(LTD_FULL_NAME)
                        .status(Status.ACTIVE)
                        .created(localDateTime)
                        .update(localDateTime)
                        .build())
                .build();
    }

    @Override
    public LtdBank getEntityWithId() {
        return LtdBank.builder()
                .id(ID)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .requisites(REQUISITES)
                .ltd(Ltd.builder()
                        .id(ID)
                        .nameFull(LTD_FULL_NAME)
                        .status(Status.ACTIVE)
                        .created(localDateTime)
                        .update(localDateTime)
                        .build())
                .build();
    }

    @Override
    public LtdBank getEntityWithIdNotActive() {
        LtdBank ltdBank = getEntityWithId();
        ltdBank.setStatus(Status.NOT_ACTIVE);
        return ltdBank;
    }
}
