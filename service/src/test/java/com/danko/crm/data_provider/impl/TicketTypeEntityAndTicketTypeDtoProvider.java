package com.danko.crm.data_provider.impl;

import com.danko.crm.data_provider.DataProviderDto;
import com.danko.crm.data_provider.DataProviderEntity;
import com.danko.crm.model.Status;
import com.danko.crm.model.TicketType;
import com.danko.crm.service.dto.TicketTypeDto;

import java.time.LocalDateTime;

public class TicketTypeEntityAndTicketTypeDtoProvider implements DataProviderDto<TicketTypeDto>, DataProviderEntity<TicketType> {
    private final static Long ID = 1L;
    private final static String NAME = "Ticket Type";
    private final static Integer ACTION_POSITIVE = 1;
    private final static Integer PRIORITY = 1;
    private final LocalDateTime localDateTime = LocalDateTime.now();

    @Override
    public TicketTypeDto getDtoWithOutId() {
        return TicketTypeDto.builder()
                .priority(PRIORITY)
                .action(ACTION_POSITIVE)
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Override
    public TicketTypeDto getDtoWithId() {
        return TicketTypeDto.builder()
                .id(ID)
                .priority(PRIORITY)
                .action(ACTION_POSITIVE)
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Override
    public TicketTypeDto getDtoWithIdNotActive() {
        TicketTypeDto ticketTypeDto = getDtoWithId();
        ticketTypeDto.setStatus(Status.NOT_ACTIVE);
        return ticketTypeDto;
    }

    @Override
    public TicketType getEntityWithIdNotActive() {
        TicketType ticketType = getEntityWithId();
        ticketType.setStatus(Status.NOT_ACTIVE);
        return ticketType;
    }

    @Override
    public TicketType getEntityWithOutId() {
        return TicketType.builder()
                .priority(PRIORITY)
                .action(ACTION_POSITIVE)
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Override
    public TicketType getEntityWithId() {
        return TicketType.builder()
                .id(ID)
                .priority(PRIORITY)
                .action(ACTION_POSITIVE)
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }
}
