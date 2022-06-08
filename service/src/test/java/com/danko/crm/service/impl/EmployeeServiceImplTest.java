package com.danko.crm.service.impl;

import com.danko.crm.data_provider.impl.EmployeeEntityAndEmployeeDtoProvider;
import com.danko.crm.model.Employee;
import com.danko.crm.model.Role;
import com.danko.crm.model.Status;
import com.danko.crm.repository.EmployeeRepository;
import com.danko.crm.repository.RoleRepository;
import com.danko.crm.service.CityService;
import com.danko.crm.service.DepartmentService;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.PositionService;
import com.danko.crm.service.dto.EmployeeDto;
import com.danko.crm.service.exception.EntityNotFoundException;
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
class EmployeeServiceImplTest {
    private final static String CITY_NAME = "Minsk";
    private final static String STRING_DATA = "data";
    private final static String EMAIL = "info@gmail.com";
    private final static String PROXY = "proxy";
    private final static Long ID = 1L;
    private final static Integer TG_ID = 12345678;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PositionService positionService;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private CityService cityService;

    @Mock
    private DtoToEntityConverterService dtoToEntityConverterService;

    @Mock
    private EntityToDtoConverterService entityToDtoConverterService;

    private final EmployeeEntityAndEmployeeDtoProvider employeeEntityAndEmployeeDtoProvider = new EmployeeEntityAndEmployeeDtoProvider();

    @Test
    void findById() {
        EmployeeDto expected = employeeEntityAndEmployeeDtoProvider.getDtoWithId();
        Employee employeeFromDb = employeeEntityAndEmployeeDtoProvider.getEntityWithId();
        Mockito.when(employeeRepository.findById(ID)).thenReturn(Optional.of(employeeFromDb));
        Mockito.when(entityToDtoConverterService.convert(employeeFromDb)).thenReturn(expected);

        EmployeeDto actual = employeeService.findById(ID);

        assertEquals(expected, actual);
    }

    @Test
    void findByIdException() {
        Mockito.when(employeeRepository.findById(ID)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> employeeService.findById(ID));
        assertEquals(ID, exception.getId());
    }

    @Test
    void save() {
        EmployeeDto expected = employeeEntityAndEmployeeDtoProvider.getDtoWithId();
        Employee employeeFromDb = employeeEntityAndEmployeeDtoProvider.getEntityWithId();

        Mockito.when(entityToDtoConverterService.convert(employeeFromDb)).thenReturn(expected);
        Mockito.when(dtoToEntityConverterService.convert(expected)).thenReturn(employeeFromDb);
        Mockito.when(employeeRepository.save(employeeFromDb)).thenReturn(employeeFromDb);
        Mockito.when(employeeRepository.findByUserName(expected.getUserName())).thenReturn(Optional.empty());

        Mockito.when(cityService.findById(expected.getCity().getId())).thenReturn(expected.getCity());
        Mockito.when(positionService.findById(expected.getPosition().getId())).thenReturn(expected.getPosition());
        Mockito.when(departmentService.findById(expected.getDepartment().getId())).thenReturn(expected.getDepartment());
        Mockito.when(roleRepository.findByName("User")).thenReturn(Optional.of(Role.builder().name("user").build()));

        EmployeeDto actual = employeeService.save(expected);

        assertEquals(expected, actual);
    }

    @Test
    void saveFindInDataBase() {
        EmployeeDto expected = employeeEntityAndEmployeeDtoProvider.getDtoWithId();
        Employee employeeFromDb = employeeEntityAndEmployeeDtoProvider.getEntityWithId();

        Mockito.when(employeeRepository.findByUserName(expected.getUserName())).thenReturn(Optional.of(employeeFromDb));
        Mockito.when(entityToDtoConverterService.convert(employeeFromDb)).thenReturn(expected);

        EmployeeDto actual = employeeService.save(expected);

        assertEquals(expected, actual);
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<Employee> employees = new PageImpl<>(Arrays.asList(employeeEntityAndEmployeeDtoProvider.getEntityWithId()));
        Page<EmployeeDto> expected = new PageImpl<>(Arrays.asList(employeeEntityAndEmployeeDtoProvider.getDtoWithId()));

        Mockito.when(employeeRepository.findAll(pageable)).thenReturn(employees);
        Mockito.when(entityToDtoConverterService.convert(employeeEntityAndEmployeeDtoProvider.getEntityWithId()))
                .thenReturn(employeeEntityAndEmployeeDtoProvider.getDtoWithId());

        Page<EmployeeDto> actual = employeeService.findAll(pageable);

        assertEquals(expected, actual);
    }

    @Test
    void findAllByStatus() {
        Status status = Status.NOT_ACTIVE;
        Pageable pageable = PageRequest.of(0, 10);

        Page<Employee> employees = new PageImpl<>(Arrays.asList(employeeEntityAndEmployeeDtoProvider.getEntityWithIdNotActive()));
        Page<EmployeeDto> expected = new PageImpl<>(Arrays.asList(employeeEntityAndEmployeeDtoProvider.getDtoWithIdNotActive()));

        Mockito.when(employeeRepository.findAllByStatus(pageable, status)).thenReturn(employees);
        Mockito.when(entityToDtoConverterService.convert(employeeEntityAndEmployeeDtoProvider.getEntityWithIdNotActive()))
                .thenReturn(employeeEntityAndEmployeeDtoProvider.getDtoWithIdNotActive());

        Page<EmployeeDto> actual = employeeService.findAllByStatus(pageable, status);

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        Status newStatus = Status.NOT_ACTIVE;
        String firstName = "New first name";
        String lastName = "New last name";
        String patronymic = "new patronymic";
        LocalDateTime birthday = LocalDateTime.now();
        LocalDateTime contractStart= LocalDateTime.now();
        LocalDateTime contractFinish= LocalDateTime.now();
        String email = "newemail@gmail.com";
        String dov = "new proxy";
        Integer tgId = 569852;
        String comment = "new comment";


        Employee employeeFromDb = employeeEntityAndEmployeeDtoProvider.getEntityWithId();

        Employee employeeFromDbAfterUpdate = employeeEntityAndEmployeeDtoProvider.getEntityWithId();
        employeeFromDbAfterUpdate.setStatus(newStatus);
        employeeFromDbAfterUpdate.setFirstName(firstName);
        employeeFromDbAfterUpdate.setLastName(lastName);
        employeeFromDbAfterUpdate.setPatronymic(patronymic);
        employeeFromDbAfterUpdate.setBirthday(birthday);
        employeeFromDbAfterUpdate.setContractStart(contractStart);
        employeeFromDbAfterUpdate.setContractFinish(contractFinish);
        employeeFromDbAfterUpdate.setEmail(email);
        employeeFromDbAfterUpdate.setDov(dov);
        employeeFromDbAfterUpdate.setTgId(tgId);
        employeeFromDbAfterUpdate.setComment(comment);

        EmployeeDto expected = employeeEntityAndEmployeeDtoProvider.getDtoWithId();
        expected.setStatus(newStatus);
        expected.setFirstName(firstName);
        expected.setLastName(lastName);
        expected.setPatronymic(patronymic);
        expected.setBirthday(birthday);
        expected.setContractStart(contractStart);
        expected.setContractFinish(contractFinish);
        expected.setEmail(email);
        expected.setDov(dov);
        expected.setTgId(tgId);
        expected.setComment(comment);

        EmployeeDto updatedEmployeeDto = employeeEntityAndEmployeeDtoProvider.getDtoWithId();
        updatedEmployeeDto.setStatus(newStatus);
        updatedEmployeeDto.setFirstName(firstName);
        updatedEmployeeDto.setLastName(lastName);
        updatedEmployeeDto.setPatronymic(patronymic);
        updatedEmployeeDto.setBirthday(birthday);
        updatedEmployeeDto.setContractStart(contractStart);
        updatedEmployeeDto.setContractFinish(contractFinish);
        updatedEmployeeDto.setEmail(email);
        updatedEmployeeDto.setDov(dov);
        updatedEmployeeDto.setTgId(tgId);
        updatedEmployeeDto.setComment(comment);


        Mockito.when(employeeRepository.findById(ID)).thenReturn(Optional.of(employeeFromDb));
        Mockito.when(employeeRepository.save(employeeFromDb)).thenReturn(employeeFromDbAfterUpdate);
        Mockito.when(entityToDtoConverterService.convert(employeeFromDbAfterUpdate)).thenReturn(updatedEmployeeDto);

        EmployeeDto actual = employeeService.update(updatedEmployeeDto);

        assertEquals(expected, actual);
    }

    @Test
    void updateFindByIdException() {
        EmployeeDto employeeDto = employeeEntityAndEmployeeDtoProvider.getDtoWithId();

        Mockito.when(employeeRepository.findById(employeeDto.getId())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> employeeService.update(employeeDto));

        assertEquals(ID, exception.getId());
    }
}
