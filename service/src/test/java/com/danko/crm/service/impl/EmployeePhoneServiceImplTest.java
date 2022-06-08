package com.danko.crm.service.impl;

import com.danko.crm.model.Employee;
import com.danko.crm.model.EmployeePhone;
import com.danko.crm.model.PhoneType;
import com.danko.crm.model.Status;
import com.danko.crm.repository.EmployeePhoneRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EmployeeService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.PhoneTypeService;
import com.danko.crm.service.dto.EmployeeDto;
import com.danko.crm.service.dto.EmployeePhoneDto;
import com.danko.crm.service.dto.PhoneTypeDto;
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
class EmployeePhoneServiceImplTest {
    private final static String NAME = "Name";
    private final static Long ID = 1L;

    @InjectMocks
    private EmployeePhoneServiceImpl employeePhoneService;

    @Mock
    private EmployeePhoneRepository employeePhoneRepository;

    @Mock
    private DtoToEntityConverterService dtoToEntityConverterService;

    @Mock
    private EntityToDtoConverterService entityToDtoConverterService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private PhoneTypeService phoneTypeService;

    private EmployeePhoneDto expected;
    private EmployeePhone employeePhoneFromDb;

    @BeforeEach
    public void init() {
        LocalDateTime localDateTime = LocalDateTime.now();
        expected = EmployeePhoneDto.builder()
                .id(ID)
                .number(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .phoneType(
                        PhoneTypeDto.builder()
                                .id(ID)
                                .name(NAME)
                                .status(Status.ACTIVE)
                                .build()
                )
                .employee(
                        EmployeeDto.builder()
                                .id(ID)
                                .status(Status.ACTIVE)
                                .build()
                )
                .build();
        employeePhoneFromDb = EmployeePhone.builder()
                .id(ID)
                .number(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .phoneType(
                        PhoneType.builder()
                                .id(ID)
                                .name(NAME)
                                .status(Status.ACTIVE)
                                .build()
                )
                .employee(
                        Employee.builder()
                                .id(ID)
                                .status(Status.ACTIVE)
                                .build()
                )
                .build();
    }

    @Test
    void findById() {
        Mockito.when(employeePhoneRepository.findById(ID)).thenReturn(Optional.of(employeePhoneFromDb));
        Mockito.when(entityToDtoConverterService.convert(employeePhoneFromDb)).thenReturn(expected);

        EmployeePhoneDto actual = employeePhoneService.findById(ID);

        assertEquals(expected, actual);
    }

    @Test
    void findByIdNegative() {
        Mockito.when(employeePhoneRepository.findById(ID))
                .thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> employeePhoneService.findById(ID));
        assertEquals(ID, exception.getId());
    }

    @Test
    void save() {
        Mockito.when(entityToDtoConverterService.convert(employeePhoneFromDb)).thenReturn(expected);
        Mockito.when(dtoToEntityConverterService.convert(expected)).thenReturn(employeePhoneFromDb);
        Mockito.when(employeePhoneRepository.save(employeePhoneFromDb)).thenReturn(employeePhoneFromDb);

        Mockito.when(employeeService.findById(expected.getEmployee().getId())).thenReturn(expected.getEmployee());
        Mockito.when(phoneTypeService.findById(expected.getPhoneType().getId())).thenReturn(expected.getPhoneType());

        EmployeePhoneDto actual = employeePhoneService.save(expected);
        assertEquals(expected, actual);
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<EmployeePhone> roles = new PageImpl<>(Arrays.asList(employeePhoneFromDb));
        Page<EmployeePhoneDto> expected = new PageImpl<>(Arrays.asList(this.expected));

        Mockito.when(employeePhoneRepository.findAll(pageable)).thenReturn(roles);
        Mockito.when(entityToDtoConverterService.convert(this.employeePhoneFromDb)).thenReturn(this.expected);

        Page<EmployeePhoneDto> actual = employeePhoneService.findAll(pageable);

        assertEquals(expected, actual);
    }

    @Test
    void findAllByStatus() {
        Pageable pageable = PageRequest.of(0, 10);
        Status status = Status.ACTIVE;

        Page<EmployeePhone> roles = new PageImpl<>(Arrays.asList(employeePhoneFromDb));
        Page<EmployeePhoneDto> expected = new PageImpl<>(Arrays.asList(this.expected));

        Mockito.when(employeePhoneRepository.findAllByStatus(pageable, status)).thenReturn(roles);
        Mockito.when(entityToDtoConverterService.convert(this.employeePhoneFromDb)).thenReturn(this.expected);

        Page<EmployeePhoneDto> actual = employeePhoneService.findAllByStatus(pageable, status);

        assertEquals(expected, actual);
    }
}
