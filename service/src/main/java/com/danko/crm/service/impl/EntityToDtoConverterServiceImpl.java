package com.danko.crm.service.impl;

import com.danko.crm.model.Car;
import com.danko.crm.model.City;
import com.danko.crm.model.Department;
import com.danko.crm.model.Employee;
import com.danko.crm.model.EmployeePhone;
import com.danko.crm.model.Ltd;
import com.danko.crm.model.LtdBank;
import com.danko.crm.model.LtdContract;
import com.danko.crm.model.LtdInstance;
import com.danko.crm.model.PhoneType;
import com.danko.crm.model.Position;
import com.danko.crm.model.Role;
import com.danko.crm.model.Ticket;
import com.danko.crm.model.TicketType;
import com.danko.crm.model.UserAuth;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.converter.entity_to_dto.CarToDtoConverter;
import com.danko.crm.service.converter.entity_to_dto.CityToDtoConverter;
import com.danko.crm.service.converter.entity_to_dto.DepartmentToDtoConverter;
import com.danko.crm.service.converter.entity_to_dto.EmployeePhoneToDtoConverter;
import com.danko.crm.service.converter.entity_to_dto.EmployeeToDtoConverter;
import com.danko.crm.service.converter.entity_to_dto.LtdBankToDtoConverter;
import com.danko.crm.service.converter.entity_to_dto.LtdContractToDtoConverter;
import com.danko.crm.service.converter.entity_to_dto.LtdInstanceToDtoConverter;
import com.danko.crm.service.converter.entity_to_dto.LtdToDtoConverter;
import com.danko.crm.service.converter.entity_to_dto.PhoneTypeToDtoConverter;
import com.danko.crm.service.converter.entity_to_dto.PositionToDtoConverter;
import com.danko.crm.service.converter.entity_to_dto.RoleToDtoConverter;
import com.danko.crm.service.converter.entity_to_dto.TicketToDtoConverter;
import com.danko.crm.service.converter.entity_to_dto.TicketTypeToDtoConverter;
import com.danko.crm.service.converter.entity_to_dto.UserAuthToDtoConverter;
import com.danko.crm.service.dto.CarDto;
import com.danko.crm.service.dto.CityDto;
import com.danko.crm.service.dto.DepartmentDto;
import com.danko.crm.service.dto.EmployeeDto;
import com.danko.crm.service.dto.EmployeePhoneDto;
import com.danko.crm.service.dto.LtdBankDto;
import com.danko.crm.service.dto.LtdContractDto;
import com.danko.crm.service.dto.LtdDto;
import com.danko.crm.service.dto.LtdInstanceDto;
import com.danko.crm.service.dto.PhoneTypeDto;
import com.danko.crm.service.dto.PositionDto;
import com.danko.crm.service.dto.RoleDto;
import com.danko.crm.service.dto.TicketDto;
import com.danko.crm.service.dto.TicketTypeDto;
import com.danko.crm.service.dto.UserAuthDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EntityToDtoConverterServiceImpl implements EntityToDtoConverterService {
    private CityToDtoConverter cityToDtoConverter;
    private DepartmentToDtoConverter departmentToDtoConverter;
    private EmployeePhoneToDtoConverter employeePhoneToDtoConverter;
    private EmployeeToDtoConverter employeeToDtoConverter;
    private PhoneTypeToDtoConverter phoneTypeToDtoConverter;
    private PositionToDtoConverter positionToDtoConverter;
    private RoleToDtoConverter roleToDtoConverter;
    private CarToDtoConverter carToDtoConverter;
    private LtdToDtoConverter ltdToDtoConverter;
    private LtdBankToDtoConverter ltdBankToDtoConverter;
    private LtdContractToDtoConverter ltdContractToDtoConverter;
    private LtdInstanceToDtoConverter ltdInstanceToDtoConverter;
    private TicketTypeToDtoConverter ticketTypeToDtoConverter;
    private TicketToDtoConverter ticketToDtoConverter;
    private UserAuthToDtoConverter userAuthToDtoConverter;

    @Override
    public CityDto convert(City city) {
        return cityToDtoConverter.convert(city);
    }

    @Override
    public DepartmentDto convert(Department department) {
        return departmentToDtoConverter.convert(department);
    }

    @Override
    public EmployeeDto convert(Employee employee) {
        EmployeeDto employeeDto = employeeToDtoConverter.convert(employee);

        if (employee.getDepartment() != null) {
            employeeDto.setDepartment(convert(employee.getDepartment()));
        }

        if (employee.getCity() != null) {
            employeeDto.setCity(convert(employee.getCity()));
        }

        if (employee.getPosition() != null) {
            employeeDto.setPosition(convert(employee.getPosition()));
        }

        if (employee.getPhones() != null && !employee.getPhones().isEmpty()) {
            employeeDto.setPhones(
                    employee.getPhones()
                            .stream()
                            .map(this::convert)
                            .collect(Collectors.toList()));
        }

        if (employee.getRoles() != null && !employee.getRoles().isEmpty()) {
            employeeDto.setRoles(
                    employee.getRoles()
                            .stream()
                            .map(this::convert)
                            .collect(Collectors.toList()));
        }
        return employeeDto;
    }

    @Override
    public EmployeePhoneDto convert(EmployeePhone employeePhone) {
        EmployeePhoneDto employeePhoneDto = employeePhoneToDtoConverter.convert(employeePhone);
        if (employeePhone.getPhoneType() != null) {
            employeePhoneDto.setPhoneType(convert(employeePhone.getPhoneType()));
        }
        return employeePhoneDto;
    }

    @Override
    public PhoneTypeDto convert(PhoneType phoneType) {
        return phoneTypeToDtoConverter.convert(phoneType);
    }

    @Override
    public PositionDto convert(Position position) {
        PositionDto positionDto = positionToDtoConverter.convert(position);
        if (position.getDepartment() != null) {
            positionDto.setDepartment(convert(position.getDepartment()));
        }
        return positionDto;
    }

    @Override
    public RoleDto convert(Role role) {
        return roleToDtoConverter.convert(role);
    }

    @Override
    public CarDto convert(Car car) {
        CarDto carDto = carToDtoConverter.convert(car);
        if (car.getCity() != null) {
            carDto.setCity(convert(car.getCity()));
        }
        return carDto;
    }

    @Override
    public LtdDto convert(Ltd ltd) {
        LtdDto ltdDto = ltdToDtoConverter.convert(ltd);
        if (ltd.getLtdBanks() != null && !ltd.getLtdBanks().isEmpty()) {
            ltdDto.setLtdBanks(ltd.getLtdBanks()
                    .stream()
                    .map(this::convert)
                    .collect(Collectors.toList()));
        }
        if (ltd.getLtdContracts() != null && !ltd.getLtdContracts().isEmpty()) {
            ltdDto.setLtdContracts(ltd.getLtdContracts()
                    .stream()
                    .map(this::convert)
                    .collect(Collectors.toList()));
        }
        return ltdDto;
    }

    @Override
    public LtdBankDto convert(LtdBank ltdBank) {
        LtdBankDto ltdBankDto = ltdBankToDtoConverter.convert(ltdBank);
        return ltdBankDto;
    }

    @Override
    public LtdContractDto convert(LtdContract ltdContract) {
        LtdContractDto ltdContractDto = ltdContractToDtoConverter.convert(ltdContract);
        return ltdContractDto;
    }

    @Override
    public LtdInstanceDto convert(LtdInstance ltdInstance) {
        LtdInstanceDto ltdInstanceDto = ltdInstanceToDtoConverter.convert(ltdInstance);
        if (ltdInstance.getLtd() != null) {
            assert ltdInstanceDto != null;
            ltdInstanceDto.setLtd(convert(ltdInstance.getLtd()));
        }
        if (ltdInstance.getEmployee() != null) {
            ltdInstanceDto.setEmployee(convert(ltdInstance.getEmployee()));
        }
        if (ltdInstance.getCity() != null) {
            ltdInstanceDto.setCity(convert(ltdInstance.getCity()));
        }
        return ltdInstanceDto;
    }

    @Override
    public TicketTypeDto convert(TicketType ticketType) {
        return ticketTypeToDtoConverter.convert(ticketType);
    }

    @Override
    public TicketDto convert(Ticket ticket) {
        TicketDto ticketDto = ticketToDtoConverter.convert(ticket);
        if (ticket.getLtdInstance() != null) {
            ticketDto.setLtdInstance(convert(ticket.getLtdInstance()));
        }
        if (ticket.getEmployeeExecutor() != null) {
            ticketDto.setEmployeeExecutor(convert(ticket.getEmployeeExecutor()));
        }
        if (ticket.getEmployeeOwner() != null) {
            ticketDto.setEmployeeOwner(convert(ticket.getEmployeeOwner()));
        }
        if (ticket.getEmployeeRecipient() != null) {
            ticketDto.setEmployeeRecipient(convert(ticket.getEmployeeRecipient()));
        }
        if (ticket.getTicketType() != null) {
            ticketDto.setTicketType(convert(ticket.getTicketType()));
        }
        if (ticket.getCar() != null) {
            ticketDto.setCar(convert(ticket.getCar()));
        }
        return ticketDto;
    }

    @Override
    public UserAuthDto convert(UserAuth userAuth) {
        UserAuthDto userAuthDto = userAuthToDtoConverter.convert(userAuth);
        if (userAuth.getRoles() != null && !userAuth.getRoles().isEmpty()) {
            userAuthDto.setRoles(
                    userAuth.getRoles()
                            .stream()
                            .map(this::convert)
                            .collect(Collectors.toList()));
        }
        return userAuthDto;
    }
}
