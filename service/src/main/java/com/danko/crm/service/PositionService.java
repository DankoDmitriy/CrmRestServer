package com.danko.crm.service;

import com.danko.crm.service.dto.PositionDto;

import java.util.List;

public interface PositionService extends BaseService<PositionDto> {
    List<PositionDto> findAllByDepartmentId(Long id);
}
