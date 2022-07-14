package com.danko.crm.service.impl;

import com.danko.crm.model.City;
import com.danko.crm.model.Department;
import com.danko.crm.model.Employee;
import com.danko.crm.model.Position;
import com.danko.crm.model.Role;
import com.danko.crm.model.Status;
import com.danko.crm.repository.EmployeeRepository;
import com.danko.crm.repository.RoleRepository;
import com.danko.crm.service.CityService;
import com.danko.crm.service.DepartmentService;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EmployeeService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.JwtRefreshTokenService;
import com.danko.crm.service.PositionService;
import com.danko.crm.service.dto.CityDto;
import com.danko.crm.service.dto.DepartmentDto;
import com.danko.crm.service.dto.EmployeeDto;
import com.danko.crm.service.dto.PositionDto;
import com.danko.crm.service.dto.RoleDto;
import com.danko.crm.service.exception.EntityNotFoundException;
import com.danko.crm.service.exception.NestedEntityInactiveException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES;
import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_NESTED_ENTITY_INACTIVE_PROPERTIES;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private static final String DEFAULT_ROLE = "User";
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;
    private PositionService positionService;
    private DepartmentService departmentService;
    private CityService cityService;
    private DtoToEntityConverterService dtoToEntityConverterService;
    private EntityToDtoConverterService entityToDtoConverterService;
    private JwtRefreshTokenService jwtRefreshTokenService;

    @Override
    public EmployeeDto findById(Long id) {
        return entityToDtoConverterService.convert(findEntityById(id));
    }

    Employee findEntityById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES, id));
    }

    @Override
    @Transactional
    public EmployeeDto save(EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findByUserName(employeeDto.getUserName());

        if (optionalEmployee.isPresent()) {
            return entityToDtoConverterService.convert(optionalEmployee.get());
        } else {

            CityDto cityDto = cityService.findById(employeeDto.getCity().getId());
            PositionDto positionDto = positionService.findById(employeeDto.getPosition().getId());
            DepartmentDto departmentDto = departmentService.findById(employeeDto.getDepartment().getId());

            if (cityDto.getStatus().equals(Status.ACTIVE)
                    && positionDto.getStatus().equals(Status.ACTIVE)
                    && departmentDto.getStatus().equals(Status.ACTIVE)) {

                LocalDateTime now = LocalDateTime.now();
                Employee newEmployee = dtoToEntityConverterService.convert(employeeDto);

                newEmployee.setCreated(now);
                newEmployee.setUpdate(now);
                newEmployee.setStatus(Status.ACTIVE);
                newEmployee.setCity(dtoToEntityConverterService.convert(cityDto));
                newEmployee.setPosition(dtoToEntityConverterService.convert(positionDto));
                newEmployee.setDepartment(dtoToEntityConverterService.convert(departmentDto));
                newEmployee.setRoles(Collections.singletonList(roleRepository.findByName(DEFAULT_ROLE).get()));

                return entityToDtoConverterService.convert(
                        employeeRepository.save(newEmployee));

            } else {
                throw new NestedEntityInactiveException(EXCEPTION_NESTED_ENTITY_INACTIVE_PROPERTIES);
            }
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Employee employee = findEntityById(id);
        employee.setStatus(Status.DELETED);
        employeeRepository.save(employee);
    }

    @Override
    public Page<EmployeeDto> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(entityToDtoConverterService::convert);
    }

    @Override
    public Page<EmployeeDto> findAllByStatus(Pageable pageable, Status status) {
        return employeeRepository.findAllByStatus(pageable, status).map(entityToDtoConverterService::convert);
    }

    @Override
    public Page<EmployeeDto> findAllByDepartmentIdAndStatus(Pageable pageable, Long id, Status status) {
        return employeeRepository.findAllByDepartment_IdAndStatus(pageable, id, status)
                .map(entityToDtoConverterService::convert);
    }

    @Override
    @Transactional
    public EmployeeDto updateRoleEmployeeDto(EmployeeDto employeeDto) {
        Employee employeeFromDb = findEntityById(employeeDto.getId());
        checkEmployeeBeforeUpdateRole(employeeFromDb, employeeDto);
        return entityToDtoConverterService.convert(employeeRepository.save(employeeFromDb));
    }

    private void checkEmployeeBeforeUpdateRole(Employee employeeFromDb, EmployeeDto employeeDto) {
        LocalDateTime now = LocalDateTime.now();
        List<Role> newUserRolesList = new ArrayList<>();
        if (employeeDto.getRoles() != null) {
            for (RoleDto role : employeeDto.getRoles()) {
                Optional<Role> optionalRole = roleRepository.findByName(role.getName());
                if (optionalRole.isPresent()) {
                    newUserRolesList.add(optionalRole.get());
                }
            }
            if (!newUserRolesList.isEmpty()) {
                employeeFromDb.setRoles(newUserRolesList);
            }
        }
        employeeFromDb.setUpdate(now);
    }

    @Override
    @Transactional
    public EmployeeDto update(EmployeeDto employeeDto) {
        Employee employeeFromDb = findEntityById(employeeDto.getId());
        checkEmployeeBeforeUpdate(employeeFromDb, employeeDto);
        return entityToDtoConverterService.convert(employeeRepository.save(employeeFromDb));
    }

    private void checkEmployeeBeforeUpdate(Employee employeeFromDb, EmployeeDto employeeDto) {
        LocalDateTime now = LocalDateTime.now();

        if (employeeDto.getFirstName() != null && !employeeDto.getFirstName().equals(employeeFromDb.getFirstName())) {
            employeeFromDb.setFirstName(employeeDto.getFirstName());
        }
        if (employeeDto.getLastName() != null && !employeeDto.getLastName().equals(employeeFromDb.getLastName())) {
            employeeFromDb.setLastName(employeeDto.getLastName());
        }
        if (employeeDto.getPatronymic() != null && !employeeDto.getPatronymic().equals(employeeFromDb.getPatronymic())) {
            employeeFromDb.setPatronymic(employeeDto.getPatronymic());
        }
        if (employeeDto.getBirthday() != null && !employeeDto.getBirthday().equals(employeeFromDb.getBirthday())) {
            employeeFromDb.setBirthday(employeeDto.getBirthday());
        }
        if (employeeDto.getContractStart() != null
                && !employeeDto.getContractStart().equals(employeeFromDb.getContractStart())) {
            employeeFromDb.setContractStart(employeeDto.getContractStart());
        }
        if (employeeDto.getContractFinish() != null
                && !employeeDto.getContractFinish().equals(employeeFromDb.getContractFinish())) {
            employeeFromDb.setContractFinish(employeeDto.getContractFinish());
        }
        if (employeeDto.getEmail() != null && !employeeDto.getEmail().equals(employeeFromDb.getEmail())) {
            employeeFromDb.setEmail(employeeDto.getEmail());
        }
        if (employeeDto.getComment() != null
                && !employeeDto.getComment().equals(employeeFromDb.getComment())) {
            employeeFromDb.setComment(employeeDto.getComment());
        }
        if (employeeDto.getDov() != null
                && !employeeDto.getDov().equals(employeeFromDb.getDov())) {
            employeeFromDb.setDov(employeeDto.getDov());
        }
        if (employeeDto.getCity() != null &&
                !employeeDto.getCity().getId().equals(employeeFromDb.getCity().getId())) {
            City city = dtoToEntityConverterService.convert(
                    cityService.findById(employeeDto.getCity().getId()));
            employeeFromDb.setCity(city);
        }
        if (employeeDto.getPosition() != null &&
                !employeeDto.getPosition().getId().equals(employeeFromDb.getPosition().getId())) {
            Position position = dtoToEntityConverterService.convert(
                    positionService.findById(employeeDto.getPosition().getId()));
            employeeFromDb.setPosition(position);
        }
        if (employeeDto.getDepartment() != null &&
                !employeeDto.getDepartment().getId().equals(employeeFromDb.getDepartment().getId())) {
            Department department = dtoToEntityConverterService.convert(
                    departmentService.findById(employeeDto.getDepartment().getId()));
            employeeFromDb.setDepartment(department);
        }
        if (employeeDto.getStatus() != null && !employeeDto.getStatus().equals(employeeFromDb.getStatus())) {
            employeeFromDb.setStatus(employeeDto.getStatus());
            if (!employeeDto.getStatus().equals(Status.ACTIVE)) {
                jwtRefreshTokenService.disableEmployeeTokens(employeeFromDb.getId());
            }
        }
        employeeFromDb.setUpdate(now);
    }
}
