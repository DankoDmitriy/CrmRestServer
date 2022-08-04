package com.danko.crm.service.impl;

import com.danko.crm.model.Car;
import com.danko.crm.model.Employee;
import com.danko.crm.model.LtdInstance;
import com.danko.crm.model.Status;
import com.danko.crm.model.Ticket;
import com.danko.crm.model.TicketType;
import com.danko.crm.repository.LtdInstanceRepository;
import com.danko.crm.repository.TicketRepository;
import com.danko.crm.service.CarService;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EmployeeService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.LtdInstanceService;
import com.danko.crm.service.TicketService;
import com.danko.crm.service.TicketTypeService;
import com.danko.crm.service.dto.CarDto;
import com.danko.crm.service.dto.EmployeeDto;
import com.danko.crm.service.dto.LtdInstanceDto;
import com.danko.crm.service.dto.TicketDto;
import com.danko.crm.service.dto.TicketTypeDto;
import com.danko.crm.service.exception.EntityCanNotUpdatedException;
import com.danko.crm.service.exception.EntityNotFoundException;
import com.danko.crm.service.exception.NestedEntityInactiveException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_ENTITY_CAN_NOT_UPDATED_PROPERTIES;
import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES;
import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_NESTED_ENTITY_INACTIVE_PROPERTIES;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private DtoToEntityConverterService dtoToEntityConverterService;
    private EntityToDtoConverterService entityToDtoConverterService;
    private TicketRepository ticketRepository;
    private CarService carService;
    private EmployeeService employeeService;
    private TicketTypeService ticketTypeService;
    private LtdInstanceService ltdInstanceService;
    private LtdInstanceRepository ltdInstanceRepository;

    @Override
    public TicketDto findById(Long id) {
        return entityToDtoConverterService.convert(findEntityById(id));
    }

    private Ticket findEntityById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES, id));
    }

    @Override
    @Transactional
    public TicketDto save(TicketDto ticketDto) {
        CarDto carDto = carService.findById(ticketDto.getCar().getId());
        LtdInstanceDto ltdInstanceDto = ltdInstanceService.findById(ticketDto.getLtdInstance().getId());
        EmployeeDto employeeExecutor = employeeService.findById(ticketDto.getEmployeeExecutor().getId());
        EmployeeDto employeeOwner = employeeService.findById(ticketDto.getEmployeeOwner().getId());
        EmployeeDto employeeRecipient = employeeService.findById(ticketDto.getEmployeeRecipient().getId());
        TicketTypeDto ticketType = ticketTypeService.findById(ticketDto.getTicketType().getId());

        if (carDto.getStatus().equals(Status.ACTIVE)
                && ltdInstanceDto.getStatus().equals(Status.ACTIVE)
                && ltdInstanceDto.getLtd().getStatus().equals(Status.ACTIVE)
                && employeeExecutor.getStatus().equals(Status.ACTIVE)
                && employeeOwner.getStatus().equals(Status.ACTIVE)
                && employeeRecipient.getStatus().equals(Status.ACTIVE)
                && ticketType.getStatus().equals(Status.ACTIVE)) {

            LocalDateTime now = LocalDateTime.now();
            Ticket ticket = dtoToEntityConverterService.convert(ticketDto);

            ticket.setCreated(now);
            ticket.setUpdate(now);
            ticket.setStatus(Status.ACTIVE);
            ticket.setOpenStatus(1);

            ticket.setEmployeeExecutor(dtoToEntityConverterService.convert(employeeExecutor));
            ticketDto.setEmployeeExecutorDov(employeeExecutor.getDov());
            ticket.setEmployeeOwner(dtoToEntityConverterService.convert(employeeOwner));
            ticket.setEmployeeRecipient(dtoToEntityConverterService.convert(employeeRecipient));
            ticket.setTicketType(dtoToEntityConverterService.convert(ticketType));
            ticket.setLtdInstance(dtoToEntityConverterService.convert(ltdInstanceDto));

            return entityToDtoConverterService.convert(ticketRepository.save(ticket));

        } else {
            throw new NestedEntityInactiveException(EXCEPTION_NESTED_ENTITY_INACTIVE_PROPERTIES);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Ticket ticket = findEntityById(id);
        ticket.setStatus(Status.DELETED);
        ticketRepository.save(ticket);
    }

    @Override
    public Page<TicketDto> findAll(Pageable pageable) {
        return ticketRepository.findAll(pageable).map(entityToDtoConverterService::convert);
    }

    @Override
    public Page<TicketDto> findAllByOpenStatus(Pageable pageable, Integer ticketOpenStatus) {
        return ticketRepository.findAllByOpenStatusAndStatus(pageable, ticketOpenStatus, Status.ACTIVE)
                .map(entityToDtoConverterService::convert);
    }

    @Override
    @Transactional
    public TicketDto update(TicketDto ticketDto) {
        Ticket ticketFromDb = findEntityById(ticketDto.getId());
        if (ticketFromDb.getOpenStatus() == 1) {
            checkTicketBeforeUpdate(ticketFromDb, ticketDto);
            if (ticketDto.getOpenStatus() == 0 &&
                    ticketFromDb.getDateAccountingDepartmentDoc() != null &&
                    ticketFromDb.getDateTransferDoc() != null &&
                    ticketFromDb.getDateCustomersDepartmentDoc() != null) {
                closeTicketAndUpdateInstanceEquipment(ticketFromDb);
            }
        } else {
            checkTicketDocumentsBeforeUpdate(ticketFromDb, ticketDto);
        }
        return entityToDtoConverterService.convert(ticketRepository.save(ticketFromDb));
    }

    @Override
    public Page<TicketDto> findAllByStatus(Pageable pageable, Status status) {
        return ticketRepository.findAllByStatus(pageable, status).map(entityToDtoConverterService::convert);
    }

    private void closeTicketAndUpdateInstanceEquipment(Ticket ticketFromDb) {
        ticketFromDb.setOpenStatus(0);
        LtdInstance ltdInstanceFromDb = ltdInstanceRepository.findById(ticketFromDb.getLtdInstance().getId())
                .orElseThrow(() ->
                        new EntityNotFoundException(EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES,
                                ticketFromDb.getLtdInstance().getId()));

        ltdInstanceFromDb.setServer(ltdInstanceFromDb.getServer()
                + (ticketFromDb.getTicketType().getAction() * ticketFromDb.getServer()));

        ltdInstanceFromDb.setSwitchs(ltdInstanceFromDb.getSwitchs()
                + (ticketFromDb.getTicketType().getAction() * ticketFromDb.getSwitchs()));

        ltdInstanceFromDb.setUps(ltdInstanceFromDb.getUps()
                + (ticketFromDb.getTicketType().getAction() * ticketFromDb.getUps()));

        ltdInstanceFromDb.setWorkplace(ltdInstanceFromDb.getWorkplace()
                + (ticketFromDb.getTicketType().getAction() * ticketFromDb.getWorkplace()));

        ltdInstanceFromDb.setEquipment(ltdInstanceFromDb.getEquipment()
                + (ticketFromDb.getTicketType().getAction() * ticketFromDb.getEquipment()));
        ltdInstanceRepository.save(ltdInstanceFromDb);
    }

    private void checkTicketBeforeUpdate(Ticket ticketFromDb, TicketDto ticketDto) {
        LocalDateTime now = LocalDateTime.now();
        if (ticketDto.getDateOfReceiving() != null &&
                !ticketDto.getDateOfReceiving().equals(ticketFromDb.getDateOfReceiving())) {
            ticketFromDb.setDateOfReceiving(ticketDto.getDateOfReceiving());
        }
        if (ticketDto.getDateOfFinish() != null &&
                !ticketDto.getDateOfFinish().equals(ticketFromDb.getDateOfFinish())) {
            ticketFromDb.setDateOfFinish(ticketDto.getDateOfFinish());
        }
        if (ticketDto.getSystemTicketId() != null &&
                !ticketDto.getSystemTicketId().equals(ticketFromDb.getSystemTicketId())) {
            ticketFromDb.setSystemTicketId(ticketDto.getSystemTicketId());
        }
        if (ticketDto.getWaybill() != null &&
                !ticketDto.getWaybill().equals(ticketFromDb.getWaybill())) {
            ticketFromDb.setWaybill(ticketDto.getWaybill());
        }
        if (ticketDto.getServer() != null &&
                !ticketDto.getServer().equals(ticketFromDb.getServer())) {
            ticketFromDb.setServer(ticketDto.getServer());
        }
        if (ticketDto.getUps() != null &&
                !ticketDto.getUps().equals(ticketFromDb.getUps())) {
            ticketFromDb.setUps(ticketDto.getUps());
        }
        if (ticketDto.getSwitchs() != null &&
                !ticketDto.getSwitchs().equals(ticketFromDb.getSwitchs())) {
            ticketFromDb.setSwitchs(ticketDto.getSwitchs());
        }
        if (ticketDto.getWorkplace() != null &&
                !ticketDto.getWorkplace().equals(ticketFromDb.getWorkplace())) {
            ticketFromDb.setWorkplace(ticketDto.getWorkplace());
        }
        if (ticketDto.getEquipment() != null &&
                !ticketDto.getEquipment().equals(ticketFromDb.getEquipment())) {
            ticketFromDb.setEquipment(ticketDto.getEquipment());
        }
        if (ticketDto.getJob() != null &&
                !ticketDto.getJob().equals(ticketFromDb.getJob())) {
            ticketFromDb.setJob(ticketDto.getJob());
        }
        if (ticketDto.getOther() != null &&
                !ticketDto.getOther().equals(ticketFromDb.getOther())) {
            ticketFromDb.setOther(ticketDto.getOther());
        }
        if (ticketDto.getCar() != null &&
                !ticketDto.getCar().getId().equals(ticketFromDb.getCar().getId())) {
            Car car = dtoToEntityConverterService.convert(
                    carService.findById(ticketDto.getCar().getId()));
            ticketFromDb.setCar(car);
        }
        if (ticketDto.getLtdInstance() != null &&
                !ticketDto.getLtdInstance().getId().equals(ticketFromDb.getLtdInstance().getId())) {
            LtdInstance ltdInstance = dtoToEntityConverterService.convert(
                    ltdInstanceService.findById(ticketDto.getLtdInstance().getId()));
            ticketFromDb.setLtdInstance(ltdInstance);
        }
        if (ticketDto.getEmployeeExecutor() != null &&
                !ticketDto.getEmployeeExecutor().getId().equals(ticketFromDb.getEmployeeExecutor().getId())) {
            Employee employee = dtoToEntityConverterService.convert(
                    employeeService.findById(ticketDto.getEmployeeExecutor().getId()));
            ticketFromDb.setEmployeeExecutorDov(employee.getDov());
            ticketFromDb.setEmployeeExecutor(employee);
        }
        if (ticketDto.getEmployeeOwner() != null &&
                !ticketDto.getEmployeeOwner().getId().equals(ticketFromDb.getEmployeeOwner().getId())) {
            Employee employee = dtoToEntityConverterService.convert(
                    employeeService.findById(ticketDto.getEmployeeOwner().getId()));
            ticketFromDb.setEmployeeOwner(employee);
        }
        if (ticketDto.getEmployeeRecipient() != null &&
                !ticketDto.getEmployeeRecipient().getId().equals(ticketFromDb.getEmployeeRecipient().getId())) {
            Employee employee = dtoToEntityConverterService.convert(
                    employeeService.findById(ticketDto.getEmployeeRecipient().getId()));
            ticketFromDb.setEmployeeRecipient(employee);
        }
        if (ticketDto.getTicketType() != null &&
                !ticketDto.getTicketType().getId().equals(ticketFromDb.getTicketType().getId())) {
            TicketType ticketType = dtoToEntityConverterService.convert(
                    ticketTypeService.findById(ticketDto.getTicketType().getId()));
            ticketFromDb.setTicketType(ticketType);
        }
        checkTicketDocumentsBeforeUpdate(ticketFromDb, ticketDto);
        ticketFromDb.setUpdate(now);
    }

    private void checkTicketDocumentsBeforeUpdate(Ticket ticketFromDb, TicketDto ticketDto) {
        if (ticketDto.getDateCustomersDepartmentDoc() != null &&
                !ticketDto.getDateCustomersDepartmentDoc().equals(ticketFromDb.getDateCustomersDepartmentDoc())) {
            ticketFromDb.setDateCustomersDepartmentDoc(ticketDto.getDateCustomersDepartmentDoc());
        }
        if (ticketDto.getDateAccountingDepartmentDoc() != null &&
                !ticketDto.getDateAccountingDepartmentDoc().equals(ticketFromDb.getDateAccountingDepartmentDoc())) {
            ticketFromDb.setDateAccountingDepartmentDoc(ticketDto.getDateAccountingDepartmentDoc());
        }
        if (ticketDto.getDateTransferDoc() != null &&
                !ticketDto.getDateTransferDoc().equals(ticketFromDb.getDateTransferDoc())) {
            ticketFromDb.setDateTransferDoc(ticketDto.getDateTransferDoc());
        }
    }
}
