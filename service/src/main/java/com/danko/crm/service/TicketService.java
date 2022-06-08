package com.danko.crm.service;

import com.danko.crm.model.Status;
import com.danko.crm.service.dto.TicketDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketService extends BaseService<TicketDto> {
    Page<TicketDto> findAllByOpenStatus(Pageable pageable, Integer ticketOpenStatus);
}
