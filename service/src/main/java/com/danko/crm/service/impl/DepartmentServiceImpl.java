package com.danko.crm.service.impl;

import com.danko.crm.model.Department;
import com.danko.crm.model.Status;
import com.danko.crm.repository.DepartmentRepository;
import com.danko.crm.service.DepartmentService;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.dto.DepartmentDto;
import com.danko.crm.service.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private DtoToEntityConverterService dtoToEntityConverterService;
    private EntityToDtoConverterService entityToDtoConverterService;

    @Override
    public DepartmentDto findById(Long id) {
        return entityToDtoConverterService.convert(findEntityById(id));
    }

    private Department findEntityById(Long id) {
        return departmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES, id)
        );
    }

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findByName(departmentDto.getName());

        if (optionalDepartment.isPresent()) {
            return entityToDtoConverterService.convert(optionalDepartment.get());
        } else {
            LocalDateTime now = LocalDateTime.now();
            departmentDto.setCreated(now);
            departmentDto.setUpdate(now);
            departmentDto.setStatus(Status.ACTIVE);
            return entityToDtoConverterService.convert(
                    departmentRepository.save(
                            dtoToEntityConverterService.convert(departmentDto)
                    ));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Department department = findEntityById(id);
        department.setStatus(Status.DELETED);
        departmentRepository.save(department);
    }

    @Override
    public Page<DepartmentDto> findAll(Pageable pageable) {
        return departmentRepository.findAll(pageable).map(entityToDtoConverterService::convert);
    }

    @Override
    public Page<DepartmentDto> findAllByStatus(Pageable pageable, Status status) {
        return departmentRepository.findAllByStatus(pageable, status).map(entityToDtoConverterService::convert);
    }

    @Override
    @Transactional
    public DepartmentDto update(DepartmentDto departmentDto) {
        Department departmentDtoFromDb = findEntityById(departmentDto.getId());
        checkCityBeforeUpdate(departmentDtoFromDb, departmentDto);
        return entityToDtoConverterService.convert(departmentRepository.save(departmentDtoFromDb));
    }

    private void checkCityBeforeUpdate(Department departmentDtoFromDb, DepartmentDto departmentDto) {
        LocalDateTime now = LocalDateTime.now();

        if (departmentDto.getName() != null && !departmentDto.getName().equals(departmentDtoFromDb.getName())) {
            departmentDtoFromDb.setName(departmentDto.getName());
        }
        if (departmentDto.getStatus() != null && !departmentDto.getStatus().equals(departmentDtoFromDb.getStatus())) {
            departmentDtoFromDb.setStatus(departmentDto.getStatus());
        }
        departmentDtoFromDb.setUpdate(now);
    }
}
