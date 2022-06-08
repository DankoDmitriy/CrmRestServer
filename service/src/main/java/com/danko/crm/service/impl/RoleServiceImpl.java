package com.danko.crm.service.impl;

import com.danko.crm.model.Role;
import com.danko.crm.model.Status;
import com.danko.crm.repository.RoleRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.RoleService;
import com.danko.crm.service.dto.RoleDto;
import com.danko.crm.service.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    private DtoToEntityConverterService dtoToEntityConverterService;
    private EntityToDtoConverterService entityToDtoConverterService;

    @Override
    public RoleDto findById(Long id) {
        return entityToDtoConverterService.convert(findEntityById(id));
    }

    private Role findEntityById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES, id));
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        return entityToDtoConverterService.convert(roleRepository.save(dtoToEntityConverterService.convert(roleDto)));
    }

    @Override
    public void deleteById(Long id) {
        Role role = findEntityById(id);
        role.setStatus(Status.DELETED);
        roleRepository.save(role);
    }

    @Override
    public Page<RoleDto> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable).map(entityToDtoConverterService::convert);
    }

    @Override
    public Page<RoleDto> findAllByStatus(Pageable pageable, Status status) {
        return roleRepository.findAllByStatus(pageable, status).map(entityToDtoConverterService::convert);
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        return null;
    }
}
