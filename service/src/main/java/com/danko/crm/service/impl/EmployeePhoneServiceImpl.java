package com.danko.crm.service.impl;

import com.danko.crm.model.EmployeePhone;
import com.danko.crm.model.Status;
import com.danko.crm.repository.EmployeePhoneRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EmployeePhoneService;
import com.danko.crm.service.EmployeeService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.PhoneTypeService;
import com.danko.crm.service.dto.EmployeeDto;
import com.danko.crm.service.dto.EmployeePhoneDto;
import com.danko.crm.service.dto.PhoneTypeDto;
import com.danko.crm.service.exception.EntityNotFoundException;
import com.danko.crm.service.exception.NestedEntityInactiveException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES;
import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_NESTED_ENTITY_INACTIVE_PROPERTIES;

@Service
@AllArgsConstructor
public class EmployeePhoneServiceImpl implements EmployeePhoneService {
    private EmployeePhoneRepository employeePhoneRepository;
    private EmployeeService employeeService;
    private PhoneTypeService phoneTypeService;
    private DtoToEntityConverterService dtoToEntityConverterService;
    private EntityToDtoConverterService entityToDtoConverterService;

    @Override
    public EmployeePhoneDto findById(Long id) {
        return entityToDtoConverterService.convert(findEntityById(id));
    }

    private EmployeePhone findEntityById(Long id) {
        return employeePhoneRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES, id));
    }

    @Override
    @Transactional
    public EmployeePhoneDto save(EmployeePhoneDto employeePhoneDto) {
        EmployeeDto employeeDto = employeeService.findById(employeePhoneDto.getEmployee().getId());
        PhoneTypeDto phoneTypeDto = phoneTypeService.findById(employeePhoneDto.getPhoneType().getId());
        if (employeeDto.getStatus().equals(Status.ACTIVE) && phoneTypeDto.getStatus().equals(Status.ACTIVE)) {
            LocalDateTime now = LocalDateTime.now();

            EmployeePhone employeePhone = dtoToEntityConverterService.convert(employeePhoneDto);
            employeePhone.setCreated(now);
            employeePhone.setUpdate(now);
            employeePhone.setStatus(Status.ACTIVE);
            employeePhone.setEmployee(dtoToEntityConverterService.convert(employeeDto));
            employeePhone.setPhoneType(dtoToEntityConverterService.convert(phoneTypeDto));

            return entityToDtoConverterService.convert(employeePhoneRepository.save(employeePhone));

        } else {
            throw new NestedEntityInactiveException(EXCEPTION_NESTED_ENTITY_INACTIVE_PROPERTIES);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        EmployeePhone employeePhone = findEntityById(id);
        employeePhone.setStatus(Status.DELETED);
        employeePhoneRepository.save(employeePhone);
    }

    @Override
    public Page<EmployeePhoneDto> findAll(Pageable pageable) {
        return employeePhoneRepository.findAll(pageable).map(entityToDtoConverterService::convert);
    }

    @Override
    public Page<EmployeePhoneDto> findAllByStatus(Pageable pageable, Status status) {
        return employeePhoneRepository.findAllByStatus(pageable, status).map(entityToDtoConverterService::convert);
    }

    @Override
    @Transactional
    public EmployeePhoneDto update(EmployeePhoneDto employeePhoneDto) {
        return null;
    }
}
