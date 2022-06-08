package com.danko.crm.service.impl;

import com.danko.crm.data_provider.impl.DepartmentEntityAndDepartmentDtoProvider;
import com.danko.crm.data_provider.impl.PositionEntityAndPositionDtoProvider;
import com.danko.crm.model.Position;
import com.danko.crm.model.Status;
import com.danko.crm.repository.PositionRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.dto.PositionDto;
import com.danko.crm.service.exception.EntityNotFoundException;
import com.danko.crm.service.exception.NestedEntityInactiveException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PositionServiceImplTest {
    private final static Long ID = 1L;

    @InjectMocks
    private PositionServiceImpl positionService;

    @Mock
    private PositionRepository positionRepository;

    @Mock
    private DepartmentServiceImpl departmentService;

    @Mock
    private DtoToEntityConverterService dtoToEntityConverterService;

    @Mock
    private EntityToDtoConverterService entityToDtoConverterService;

    private final PositionEntityAndPositionDtoProvider positionEntityAndPositionDtoProvider = new PositionEntityAndPositionDtoProvider();
    private final DepartmentEntityAndDepartmentDtoProvider departmentEntityAndDepartmentDtoProvider = new DepartmentEntityAndDepartmentDtoProvider();

    @Test
    void findAllByDepartmentId() {
        List<PositionDto> expected = Arrays.asList(positionEntityAndPositionDtoProvider.getDtoWithId());

        Mockito.when(positionRepository.findAllByDepartment_Id(ID))
                .thenReturn(Arrays.asList(positionEntityAndPositionDtoProvider.getEntityWithId()));

        Mockito.when(entityToDtoConverterService.convert(positionEntityAndPositionDtoProvider.getEntityWithId()))
                .thenReturn(positionEntityAndPositionDtoProvider.getDtoWithId());

        List<PositionDto> actual = positionService.findAllByDepartmentId(ID);
        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        PositionDto expected = positionEntityAndPositionDtoProvider.getDtoWithId();
        Position positionFromDb = positionEntityAndPositionDtoProvider.getEntityWithId();

        Mockito.when(positionRepository.findById(expected.getId())).thenReturn(Optional.of(positionFromDb));

        Mockito.when(entityToDtoConverterService.convert(positionFromDb)).thenReturn(expected);

        PositionDto actual = positionService.findById(expected.getId());

        assertEquals(expected, actual);
    }

    @Test
    void findByIdException() {
        Mockito.when(positionRepository.findById(ID)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> positionService.findById(ID));
        assertEquals(ID, exception.getId());
    }

    @Test
    void save() {
        PositionDto expected = positionEntityAndPositionDtoProvider.getDtoWithId();
        Position positionFromDb = positionEntityAndPositionDtoProvider.getEntityWithId();

        Mockito.when(positionRepository.findByName(expected.getName())).thenReturn(Optional.empty());
        Mockito.when(departmentService.findById(expected.getDepartment().getId())).thenReturn(expected.getDepartment());
        Mockito.when(dtoToEntityConverterService.convert(expected)).thenReturn(positionFromDb);
        Mockito.when(dtoToEntityConverterService.convert(expected.getDepartment())).thenReturn(positionFromDb.getDepartment());

        Mockito.when(entityToDtoConverterService.convert(positionFromDb)).thenReturn(expected);

        Mockito.when(positionRepository.save(positionFromDb)).thenReturn(positionFromDb);

        PositionDto actual = positionService.save(expected);

        assertEquals(expected, actual);
    }

    @Test()
    void saveException() {
        PositionDto expected = positionEntityAndPositionDtoProvider.getDtoWithId();

        Mockito.when(departmentService.findById(departmentEntityAndDepartmentDtoProvider.getDtoWithIdNotActive().getId()))
                .thenReturn(departmentEntityAndDepartmentDtoProvider.getDtoWithIdNotActive());


        NestedEntityInactiveException exception = assertThrows(NestedEntityInactiveException.class, () -> {
            positionService.save(expected);
        });
        assertNotNull(exception.getErrorMessage());
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<Position> positions = new PageImpl<>(Arrays.asList(positionEntityAndPositionDtoProvider.getEntityWithId()));
        Page<PositionDto> expected = new PageImpl<>(Arrays.asList(positionEntityAndPositionDtoProvider.getDtoWithId()));

        Mockito.when(positionRepository.findAll(pageable)).thenReturn(positions);
        Mockito.when(entityToDtoConverterService.convert(positionEntityAndPositionDtoProvider.getEntityWithId()))
                .thenReturn(positionEntityAndPositionDtoProvider.getDtoWithId());

        Page<PositionDto> actual = positionService.findAll(pageable);

        assertEquals(expected, actual);
    }

    @Test
    void findAllByStatus() {
        Status status = Status.NOT_ACTIVE;
        Pageable pageable = PageRequest.of(0, 10);

        Page<Position> positions = new PageImpl<>(Arrays.asList(positionEntityAndPositionDtoProvider.getEntityWithIdNotActive()));
        Page<PositionDto> expected = new PageImpl<>(Arrays.asList(positionEntityAndPositionDtoProvider.getDtoWithIdNotActive()));

        Mockito.when(positionRepository.findAllByStatus(pageable, status)).thenReturn(positions);
        Mockito.when(entityToDtoConverterService.convert(positionEntityAndPositionDtoProvider.getEntityWithIdNotActive()))
                .thenReturn(positionEntityAndPositionDtoProvider.getDtoWithIdNotActive());

        Page<PositionDto> actual = positionService.findAllByStatus(pageable, status);

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        String newName = "new position name";
        String newDescription = "new Other";
        Integer newSubordinationLevel = 33;
        Status newStatus = Status.NOT_ACTIVE;

        Position positionFromDb = positionEntityAndPositionDtoProvider.getEntityWithId();

        Position positionFromDbAfterUpdate = positionEntityAndPositionDtoProvider.getEntityWithId();
        positionFromDbAfterUpdate.setName(newName);
        positionFromDbAfterUpdate.setDescription(newDescription);
        positionFromDbAfterUpdate.setSubordinationLevel(newSubordinationLevel);
        positionFromDbAfterUpdate.setStatus(newStatus);

        PositionDto expected = positionEntityAndPositionDtoProvider.getDtoWithId();
        expected.setName(newName);
        expected.setDescription(newDescription);
        expected.setSubordinationLevel(newSubordinationLevel);
        expected.setStatus(newStatus);

        PositionDto updatedPositionDto = positionEntityAndPositionDtoProvider.getDtoWithId();
        updatedPositionDto.setName(newName);
        updatedPositionDto.setDescription(newDescription);
        updatedPositionDto.setSubordinationLevel(newSubordinationLevel);
        updatedPositionDto.setStatus(newStatus);

        Mockito.when(positionRepository.findById(ID)).thenReturn(Optional.of(positionFromDb));
        Mockito.when(positionRepository.save(positionFromDb)).thenReturn(positionFromDbAfterUpdate);
        Mockito.when(entityToDtoConverterService.convert(positionFromDbAfterUpdate)).thenReturn(updatedPositionDto);

        PositionDto actual = positionService.update(updatedPositionDto);

        assertEquals(expected, actual);
    }

    @Test
    void updateFindByIdException() {
        PositionDto positionDto = positionEntityAndPositionDtoProvider.getDtoWithId();

        Mockito.when(positionRepository.findById(positionDto.getId())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> positionService.update(positionDto));

        assertEquals(ID, exception.getId());
    }
}
