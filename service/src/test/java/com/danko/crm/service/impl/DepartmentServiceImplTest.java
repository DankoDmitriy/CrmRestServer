package com.danko.crm.service.impl;

import com.danko.crm.data_provider.impl.CityEntityAndCityDtoProvider;
import com.danko.crm.data_provider.impl.DepartmentEntityAndDepartmentDtoProvider;
import com.danko.crm.model.City;
import com.danko.crm.model.Department;
import com.danko.crm.model.Status;
import com.danko.crm.repository.DepartmentRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.dto.CityDto;
import com.danko.crm.service.dto.DepartmentDto;
import com.danko.crm.service.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    private final static String DEPARTMENT_NAME = "Department";
    private final static Long ID = 1L;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DtoToEntityConverterService dtoToEntityConverterService;

    @Mock
    private EntityToDtoConverterService entityToDtoConverterService;

    private final DepartmentEntityAndDepartmentDtoProvider departmentEntityAndDepartmentDtoProvider = new DepartmentEntityAndDepartmentDtoProvider();

    @Test
    void findById() {
        DepartmentDto expected = departmentEntityAndDepartmentDtoProvider.getDtoWithId();
        Department departmentFromDb = departmentEntityAndDepartmentDtoProvider.getEntityWithId();
        Mockito.when(departmentRepository.findById(ID)).thenReturn(Optional.of(departmentFromDb));
        Mockito.when(entityToDtoConverterService.convert(departmentFromDb)).thenReturn(expected);

        DepartmentDto actual = departmentService.findById(ID);

        assertEquals(expected, actual);
    }

    @Test
    void findByIdException() {
        Mockito.when(departmentRepository.findById(ID)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> departmentService.findById(ID));
        assertEquals(ID, exception.getId());
    }

    @Test
    void save() {
        DepartmentDto expected = departmentEntityAndDepartmentDtoProvider.getDtoWithId();
        Department departmentFromDb = departmentEntityAndDepartmentDtoProvider.getEntityWithId();

        Mockito.when(entityToDtoConverterService.convert(departmentFromDb)).thenReturn(expected);
        Mockito.when(dtoToEntityConverterService.convert(expected)).thenReturn(departmentFromDb);
        Mockito.when(departmentRepository.save(departmentFromDb)).thenReturn(departmentFromDb);
        Mockito.when(departmentRepository.findByName(expected.getName())).thenReturn(Optional.empty());

        DepartmentDto actual = departmentService.save(expected);

        assertEquals(expected, actual);
    }

    @Test
    void saveFindInDataBase() {
        DepartmentDto expected = departmentEntityAndDepartmentDtoProvider.getDtoWithId();
        Department departmentFromDb = departmentEntityAndDepartmentDtoProvider.getEntityWithId();

        Mockito.when(departmentRepository.findByName(expected.getName())).thenReturn(Optional.of(departmentFromDb));
        Mockito.when(entityToDtoConverterService.convert(departmentFromDb)).thenReturn(expected);

        DepartmentDto actual = departmentService.save(expected);

        assertEquals(expected, actual);
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<Department> departments = new PageImpl<>(Arrays.asList(departmentEntityAndDepartmentDtoProvider.getEntityWithId()));
        Page<DepartmentDto> expected = new PageImpl<>(Arrays.asList(departmentEntityAndDepartmentDtoProvider.getDtoWithId()));

        Mockito.when(departmentRepository.findAll(pageable)).thenReturn(departments);
        Mockito.when(entityToDtoConverterService.convert(departmentEntityAndDepartmentDtoProvider.getEntityWithId()))
                .thenReturn(departmentEntityAndDepartmentDtoProvider.getDtoWithId());

        Page<DepartmentDto> actual = departmentService.findAll(pageable);

        assertEquals(expected, actual);
    }

    @Test
    void findAllByStatus() {
        Status status = Status.NOT_ACTIVE;
        Pageable pageable = PageRequest.of(0, 10);

        Page<Department> departments = new PageImpl<>(Arrays.asList(departmentEntityAndDepartmentDtoProvider.getEntityWithIdNotActive()));
        Page<DepartmentDto> expected = new PageImpl<>(Arrays.asList(departmentEntityAndDepartmentDtoProvider.getDtoWithIdNotActive()));

        Mockito.when(departmentRepository.findAllByStatus(pageable, status)).thenReturn(departments);
        Mockito.when(entityToDtoConverterService.convert(departmentEntityAndDepartmentDtoProvider.getEntityWithIdNotActive()))
                .thenReturn(departmentEntityAndDepartmentDtoProvider.getDtoWithIdNotActive());

        Page<DepartmentDto> actual = departmentService.findAllByStatus(pageable, status);

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        String newDepartmentName = "new department name";
        Status newStatus = Status.NOT_ACTIVE;

        Department departmentFromDb = departmentEntityAndDepartmentDtoProvider.getEntityWithId();

        Department departmentFromDbAfterUpdate = departmentEntityAndDepartmentDtoProvider.getEntityWithId();
        departmentFromDbAfterUpdate.setName(newDepartmentName);
        departmentFromDbAfterUpdate.setStatus(newStatus);

        DepartmentDto expected = departmentEntityAndDepartmentDtoProvider.getDtoWithId();
        expected.setName(newDepartmentName);
        expected.setStatus(newStatus);

        DepartmentDto updatedDepartmentDto = departmentEntityAndDepartmentDtoProvider.getDtoWithId();
        updatedDepartmentDto.setName(newDepartmentName);
        updatedDepartmentDto.setStatus(newStatus);


        Mockito.when(departmentRepository.findById(ID)).thenReturn(Optional.of(departmentFromDb));
        Mockito.when(departmentRepository.save(departmentFromDb)).thenReturn(departmentFromDbAfterUpdate);
        Mockito.when(entityToDtoConverterService.convert(departmentFromDbAfterUpdate)).thenReturn(updatedDepartmentDto);

        DepartmentDto actual = departmentService.update(updatedDepartmentDto);

        assertEquals(expected, actual);
    }

    @Test
    void updateFindByIdException() {
        DepartmentDto departmentDto= departmentEntityAndDepartmentDtoProvider.getDtoWithId();

        Mockito.when(departmentRepository.findById(departmentDto.getId())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> departmentService.update(departmentDto));

        assertEquals(ID, exception.getId());
    }
}