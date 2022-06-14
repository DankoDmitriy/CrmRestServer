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
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.converter.dto_to_entity.DtoToCarConverter;
import com.danko.crm.service.converter.dto_to_entity.DtoToCityConverter;
import com.danko.crm.service.converter.dto_to_entity.DtoToDepartmentConverter;
import com.danko.crm.service.converter.dto_to_entity.DtoToEmployeeConverter;
import com.danko.crm.service.converter.dto_to_entity.DtoToEmployeePhoneConverter;
import com.danko.crm.service.converter.dto_to_entity.DtoToLtdBankConverter;
import com.danko.crm.service.converter.dto_to_entity.DtoToLtdContractConverter;
import com.danko.crm.service.converter.dto_to_entity.DtoToLtdConverter;
import com.danko.crm.service.converter.dto_to_entity.DtoToLtdInstanceConverter;
import com.danko.crm.service.converter.dto_to_entity.DtoToPhoneTypeConverter;
import com.danko.crm.service.converter.dto_to_entity.DtoToPositionConverter;
import com.danko.crm.service.converter.dto_to_entity.DtoToRoleConverter;
import com.danko.crm.service.converter.dto_to_entity.DtoToTicketConverter;
import com.danko.crm.service.converter.dto_to_entity.DtoToTicketTypeConverter;
import com.danko.crm.service.converter.dto_to_entity.DtoToUserAuthConverter;
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
public class DtoToEntityConverterServiceImpl implements DtoToEntityConverterService {
    private DtoToEmployeePhoneConverter dtoToEmployeePhoneConverter;
    private DtoToPhoneTypeConverter dtoToPhoneTypeConverter;
    private DtoToEmployeeConverter dtoToEmployeeConverter;
    private DtoToDepartmentConverter dtoToDepartmentConverter;
    private DtoToCityConverter dtoToCityConverter;
    private DtoToPositionConverter dtoToPositionConverter;
    private DtoToRoleConverter dtoToRoleConverter;
    private DtoToCarConverter dtoToCarConverter;
    private DtoToLtdConverter dtoToLtdConverter;
    private DtoToLtdBankConverter dtoToLtdBankConverter;
    private DtoToLtdContractConverter dtoToLtdContractConverter;
    private DtoToLtdInstanceConverter dtoToLtdInstanceConverter;
    private DtoToTicketTypeConverter dtoToTicketTypeConverter;
    private DtoToTicketConverter dtoToTicketConverter;
    private DtoToUserAuthConverter dtoToUserAuthConverter;

    @Override
    public City convert(CityDto cityDto) {
        return dtoToCityConverter.convert(cityDto);
    }

    @Override
    public Department convert(DepartmentDto departmentDto) {
        Department department = dtoToDepartmentConverter.convert(departmentDto);
        if (departmentDto.getPositions() != null && !departmentDto.getPositions().isEmpty()) {
            department.setPositions(
                    departmentDto.getPositions()
                            .stream()
                            .map(this::convert)
                            .collect(Collectors.toList()));
        }
        return department;
    }

    @Override
    public Employee convert(EmployeeDto employeeDto) {
        Employee employee = dtoToEmployeeConverter.convert(employeeDto);

        if (employeeDto.getDepartment() != null) {
            employee.setDepartment(convert(employeeDto.getDepartment()));
        }

        if (employeeDto.getCity() != null) {
            employee.setCity(convert(employeeDto.getCity()));
        }

        if (employeeDto.getPosition() != null) {
            employee.setPosition(convert(employeeDto.getPosition()));
        }

        if (employeeDto.getPhones() != null && !employeeDto.getPhones().isEmpty()) {
            employee.setPhones(
                    employeeDto.getPhones()
                            .stream()
                            .map(this::convert)
                            .collect(Collectors.toList()));
        }

        if (employeeDto.getRoles() != null && !employeeDto.getRoles().isEmpty()) {
            employee.setRoles(
                    employeeDto.getRoles()
                            .stream()
                            .map(this::convert)
                            .collect(Collectors.toList()));
        }
        return employee;
    }

    @Override
    public EmployeePhone convert(EmployeePhoneDto employeePhoneDto) {
        EmployeePhone employeePhone = dtoToEmployeePhoneConverter.convert(employeePhoneDto);
        if (employeePhoneDto.getPhoneType() != null) {
            employeePhone.setPhoneType(convert(employeePhoneDto.getPhoneType()));
        }
        return employeePhone;
    }

    @Override
    public PhoneType convert(PhoneTypeDto phoneTypeDto) {
        return dtoToPhoneTypeConverter.convert(phoneTypeDto);
    }

    @Override
    public Position convert(PositionDto positionDto) {
        Position position = dtoToPositionConverter.convert(positionDto);
        return position;
    }

    @Override
    public Role convert(RoleDto roleDto) {
        return dtoToRoleConverter.convert(roleDto);
    }

    @Override
    public Car convert(CarDto carDto) {
        Car car = dtoToCarConverter.convert(carDto);
        if (carDto.getCity() != null) {
            car.setCity(convert(carDto.getCity()));
        }
        return car;
    }

    @Override
    public Ltd convert(LtdDto ltdDto) {
        Ltd ltd = dtoToLtdConverter.convert(ltdDto);

        if (ltdDto.getLtdBanks() != null && !ltdDto.getLtdBanks().isEmpty()) {
            ltd.setLtdBanks(ltdDto.getLtdBanks()
                    .stream()
                    .map(this::convert)
                    .collect(Collectors.toList()));
        }
        if (ltdDto.getLtdContracts() != null && !ltdDto.getLtdContracts().isEmpty()) {
            ltd.setLtdContracts(ltdDto.getLtdContracts()
                    .stream()
                    .map(this::convert)
                    .collect(Collectors.toList()));
        }
        return ltd;
    }

    @Override
    public LtdBank convert(LtdBankDto ltdBankDto) {
        return dtoToLtdBankConverter.convert(ltdBankDto);
    }

    @Override
    public LtdContract convert(LtdContractDto ltdContractDto) {
        return dtoToLtdContractConverter.convert(ltdContractDto);
    }

    @Override
    public LtdInstance convert(LtdInstanceDto ltdInstanceDto) {
        LtdInstance ltdInstance = dtoToLtdInstanceConverter.convert(ltdInstanceDto);
        if (ltdInstanceDto.getLtd() != null) {
            ltdInstance.setLtd(convert(ltdInstanceDto.getLtd()));
        }
        if (ltdInstanceDto.getEmployee() != null) {
            ltdInstance.setEmployee(convert(ltdInstanceDto.getEmployee()));
        }
        if (ltdInstanceDto.getCity() != null) {
            ltdInstance.setCity(convert(ltdInstanceDto.getCity()));
        }
        return ltdInstance;
    }

    @Override
    public TicketType convert(TicketTypeDto ticketTypeDto) {
        return dtoToTicketTypeConverter.convert(ticketTypeDto);
    }

    @Override
    public Ticket convert(TicketDto ticketDto) {
        Ticket ticket = dtoToTicketConverter.convert(ticketDto);
        if (ticketDto.getLtdInstance() != null) {
            ticket.setLtdInstance(convert(ticketDto.getLtdInstance()));
        }
        if (ticketDto.getEmployeeExecutor() != null) {
            ticket.setEmployeeExecutor(convert(ticketDto.getEmployeeExecutor()));
        }
        if (ticketDto.getEmployeeOwner() != null) {
            ticket.setEmployeeOwner(convert(ticketDto.getEmployeeOwner()));
        }
        if (ticketDto.getEmployeeRecipient() != null) {
            ticket.setEmployeeRecipient(convert(ticketDto.getEmployeeRecipient()));
        }
        if (ticketDto.getTicketType() != null) {
            ticket.setTicketType(convert(ticketDto.getTicketType()));
        }
        if (ticketDto.getCar() != null) {
            ticket.setCar(convert(ticketDto.getCar()));
        }
        return ticket;
    }

    @Override
    public UserAuth convert(UserAuthDto userAuthDto) {
        UserAuth userAuth = dtoToUserAuthConverter.convert(userAuthDto);
        if (userAuthDto.getRoles() != null && !userAuthDto.getRoles().isEmpty()) {
            userAuth.setRoles(
                    userAuthDto.getRoles()
                            .stream()
                            .map(this::convert)
                            .collect(Collectors.toList()));
        }
        return userAuth;
    }
}
