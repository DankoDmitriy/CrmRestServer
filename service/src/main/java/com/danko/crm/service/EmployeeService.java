package com.danko.crm.service;

import com.danko.crm.model.Status;
import com.danko.crm.service.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService extends BaseService<EmployeeDto> {
    EmployeeDto updateRoleEmployeeDto(EmployeeDto employeeDto);

    Page<EmployeeDto> findAllByDepartmentIdAndStatus(Pageable pageable, Long id, Status status);
}
