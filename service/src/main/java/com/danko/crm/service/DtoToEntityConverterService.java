package com.danko.crm.service;

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

public interface DtoToEntityConverterService {
    City convert(CityDto cityDto);

    Department convert(DepartmentDto departmentDto);

    Employee convert(EmployeeDto employeeDto);

    EmployeePhone convert(EmployeePhoneDto employeePhoneDto);

    PhoneType convert(PhoneTypeDto phoneTypeDto);

    Position convert(PositionDto positionDto);

    Role convert(RoleDto roleDto);

    Car convert(CarDto carDto);

    Ltd convert(LtdDto ltdDto);

    LtdBank convert(LtdBankDto ltdBankDto);

    LtdContract convert(LtdContractDto ltdContractDto);

    LtdInstance convert(LtdInstanceDto ltdInstanceDto);

    TicketType convert(TicketTypeDto ticketTypeDto);

    Ticket convert(TicketDto ticketDto);

    UserAuth convert(UserAuthDto userAuthDto);
}
