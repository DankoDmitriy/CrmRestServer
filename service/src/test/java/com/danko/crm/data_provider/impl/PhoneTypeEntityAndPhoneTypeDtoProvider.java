package com.danko.crm.data_provider.impl;

import com.danko.crm.data_provider.DataProviderDto;
import com.danko.crm.data_provider.DataProviderEntity;
import com.danko.crm.model.PhoneType;
import com.danko.crm.model.Status;
import com.danko.crm.service.dto.PhoneTypeDto;

import java.time.LocalDateTime;

public class PhoneTypeEntityAndPhoneTypeDtoProvider implements DataProviderDto<PhoneTypeDto>, DataProviderEntity<PhoneType> {
    private final static Long ID = 1L;
    private final static String NAME = "Phone Type";
    private final LocalDateTime localDateTime = LocalDateTime.now();

    @Override
    public PhoneTypeDto getDtoWithOutId() {
        return PhoneTypeDto.builder()
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Override
    public PhoneTypeDto getDtoWithIdNotActive() {
        PhoneTypeDto phoneTypeDto = getDtoWithId();
        phoneTypeDto.setStatus(Status.NOT_ACTIVE);
        return phoneTypeDto;
    }

    @Override
    public PhoneType getEntityWithIdNotActive() {
        PhoneType phoneType = getEntityWithId();
        phoneType.setStatus(Status.NOT_ACTIVE);
        return phoneType;
    }

    @Override
    public PhoneTypeDto getDtoWithId() {
        return PhoneTypeDto.builder()
                .id(ID)
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Override
    public PhoneType getEntityWithOutId() {
        return PhoneType.builder()
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Override
    public PhoneType getEntityWithId() {
        return PhoneType.builder()
                .id(ID)
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }
}
