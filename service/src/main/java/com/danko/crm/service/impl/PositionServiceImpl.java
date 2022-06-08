package com.danko.crm.service.impl;

import com.danko.crm.model.Department;
import com.danko.crm.model.Position;
import com.danko.crm.model.Status;
import com.danko.crm.repository.PositionRepository;
import com.danko.crm.service.DepartmentService;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.PositionService;
import com.danko.crm.service.dto.DepartmentDto;
import com.danko.crm.service.dto.PositionDto;
import com.danko.crm.service.exception.EntityNotFoundException;
import com.danko.crm.service.exception.NestedEntityInactiveException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES;
import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_NESTED_ENTITY_INACTIVE_PROPERTIES;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {
    private PositionRepository positionRepository;
    private DepartmentService departmentService;
    private DtoToEntityConverterService dtoToEntityConverterService;
    private EntityToDtoConverterService entityToDtoConverterService;

    @Override
    public List<PositionDto> findAllByDepartmentId(Long id) {
        List<Position> positions = positionRepository.findAllByDepartment_Id(id);
        return positions.stream().map(entityToDtoConverterService::convert)
                .collect(Collectors.toList());
    }

    @Override
    public PositionDto findById(Long id) {
        return entityToDtoConverterService.convert(findEntityById(id));
    }

    private Position findEntityById(Long id) {
        return positionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES, id));
    }

    @Override
    @Transactional
    public PositionDto save(PositionDto positionDto) {
        Optional<Position> optionalPosition = positionRepository.findByName(positionDto.getName());

        if (optionalPosition.isPresent()) {
            return entityToDtoConverterService.convert(optionalPosition.get());
        } else {
            DepartmentDto departmentDto = departmentService.findById(positionDto.getDepartment().getId());

            if (departmentDto.getStatus().equals(Status.ACTIVE)) {
                LocalDateTime now = LocalDateTime.now();
                Position position = dtoToEntityConverterService.convert(positionDto);

                position.setCreated(now);
                position.setUpdate(now);
                position.setStatus(Status.ACTIVE);
                position.setDepartment(dtoToEntityConverterService.convert(departmentDto));

                return entityToDtoConverterService.convert(positionRepository.save(position));
            } else {
                throw new NestedEntityInactiveException(EXCEPTION_NESTED_ENTITY_INACTIVE_PROPERTIES);
            }
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Position position = findEntityById(id);
        position.setStatus(Status.DELETED);
        positionRepository.save(position);
    }

    @Override
    public Page<PositionDto> findAll(Pageable pageable) {
        return positionRepository.findAll(pageable).map(entityToDtoConverterService::convert);
    }

    @Override
    public Page<PositionDto> findAllByStatus(Pageable pageable, Status status) {
        return positionRepository.findAllByStatus(pageable, status).map(entityToDtoConverterService::convert);
    }

    @Override
    @Transactional
    public PositionDto update(PositionDto positionDto) {
        Position positionFromDb = findEntityById(positionDto.getId());
        checkPositionBeforeUpdate(positionFromDb, positionDto);
        return entityToDtoConverterService.convert(positionRepository.save(positionFromDb));
    }

    private void checkPositionBeforeUpdate(Position positionFromDb, PositionDto positionDto) {
        LocalDateTime now = LocalDateTime.now();

        if (positionDto.getName() != null && !positionDto.getName().equals(positionFromDb.getName())) {
            positionFromDb.setName(positionDto.getName());
        }
        if (positionDto.getDescription() != null &&
                !positionDto.getDescription().equals(positionFromDb.getDescription())) {
            positionFromDb.setDescription(positionDto.getDescription());
        }
        if (positionDto.getSubordinationLevel() != null &&
                !positionDto.getSubordinationLevel().equals(positionFromDb.getSubordinationLevel())) {
            positionFromDb.setSubordinationLevel(positionDto.getSubordinationLevel());
        }
        if (positionDto.getStatus() != null && !positionDto.getStatus().equals(positionFromDb.getStatus())) {
            positionFromDb.setStatus(positionDto.getStatus());
        }
        if (positionDto.getDepartment() != null &&
                !positionDto.getDepartment().getId().equals(positionFromDb.getDepartment().getId())) {
            Department department = dtoToEntityConverterService.convert(
                    departmentService.findById(positionDto.getDepartment().getId()));
            positionFromDb.setDepartment(department);
        }
        positionFromDb.setUpdate(now);
    }
}
