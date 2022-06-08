package com.danko.crm.service;

import com.danko.crm.model.Status;
import com.danko.crm.service.dto.LtdInstanceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LtdInstanceService extends BaseService<LtdInstanceDto> {
    Page<LtdInstanceDto> findAllByStatusAndLtdId(Pageable pageable, Status status, Long id);
}
