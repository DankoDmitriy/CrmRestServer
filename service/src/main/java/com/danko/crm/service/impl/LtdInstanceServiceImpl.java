package com.danko.crm.service.impl;

import com.danko.crm.model.Employee;
import com.danko.crm.model.LtdInstance;
import com.danko.crm.model.Status;
import com.danko.crm.repository.LtdInstanceRepository;
import com.danko.crm.service.CityService;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EmployeeService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.LtdInstanceService;
import com.danko.crm.service.LtdService;
import com.danko.crm.service.dto.CityDto;
import com.danko.crm.service.dto.EmployeeDto;
import com.danko.crm.service.dto.LtdDto;
import com.danko.crm.service.dto.LtdInstanceDto;
import com.danko.crm.service.exception.EntityNotFoundException;
import com.danko.crm.service.exception.NestedEntityInactiveException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES;
import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_NESTED_ENTITY_INACTIVE_PROPERTIES;

@Service
@AllArgsConstructor
public class LtdInstanceServiceImpl implements LtdInstanceService {
    private DtoToEntityConverterService dtoToEntityConverterService;
    private EntityToDtoConverterService entityToDtoConverterService;
    private LtdInstanceRepository ltdInstanceRepository;
    private LtdService ltdService;
    private EmployeeService employeeService;
    private CityService cityService;

    @Override
    public LtdInstanceDto findById(Long id) {
        return entityToDtoConverterService.convert(findEntityById(id));
    }

    private LtdInstance findEntityById(Long id) {
        return ltdInstanceRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES, id));
    }

    @Override
    @Transactional
    public LtdInstanceDto save(LtdInstanceDto ltdInstanceDto) {

        CityDto cityDto = cityService.findById(ltdInstanceDto.getCity().getId());
        LtdDto ltdDto = ltdService.findById(ltdInstanceDto.getLtd().getId());
        EmployeeDto employeeDto = employeeService.findById(ltdInstanceDto.getEmployee().getId());

        if (cityDto.getStatus().equals(Status.ACTIVE)
                && ltdDto.getStatus().equals(Status.ACTIVE)
                && employeeDto.getStatus().equals(Status.ACTIVE)) {

            LocalDateTime now = LocalDateTime.now();
            LtdInstance newLtdInstance = dtoToEntityConverterService.convert(ltdInstanceDto);

            newLtdInstance.setCreated(now);
            newLtdInstance.setUpdate(now);
            newLtdInstance.setStatus(Status.ACTIVE);
            newLtdInstance.setCity(dtoToEntityConverterService.convert(cityDto));
            newLtdInstance.setLtd(dtoToEntityConverterService.convert(ltdDto));
            newLtdInstance.setEmployee(dtoToEntityConverterService.convert(employeeDto));

            return entityToDtoConverterService.convert(ltdInstanceRepository.save(newLtdInstance));
        } else {
            throw new NestedEntityInactiveException(EXCEPTION_NESTED_ENTITY_INACTIVE_PROPERTIES);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        LtdInstance ltdInstance = findEntityById(id);
        ltdInstance.setStatus(Status.DELETED);
        ltdInstanceRepository.save(ltdInstance);
    }

    @Override
    public Page<LtdInstanceDto> findAll(Pageable pageable) {
        return ltdInstanceRepository.findAll(pageable).map(entityToDtoConverterService::convert);
    }

    @Override
    @Transactional
    public LtdInstanceDto update(LtdInstanceDto ltdInstanceDto) {
        LtdInstance ltdInstanceFromDb = findEntityById(ltdInstanceDto.getId());
        checkLtdInstanceBeforeUpdate(ltdInstanceFromDb, ltdInstanceDto);
        return entityToDtoConverterService.convert(ltdInstanceRepository.save(ltdInstanceFromDb));
    }

    @Override
    public Page<LtdInstanceDto> findAllByStatus(Pageable pageable, Status status) {
        return ltdInstanceRepository.findAllByStatus(pageable, status).map(entityToDtoConverterService::convert);
    }

    @Override
    public Page<LtdInstanceDto> findAllByStatusAndLtdId(Pageable pageable, Status status, Long id) {
        return ltdInstanceRepository.findAllByLtd_Id_AndStatus(pageable, id, status).map(entityToDtoConverterService::convert);
    }

    private void checkLtdInstanceBeforeUpdate(LtdInstance ltdInstanceFromDb, LtdInstanceDto ltdInstanceDto) {
        LocalDateTime now = LocalDateTime.now();
        if (ltdInstanceDto.getType() != null && !ltdInstanceDto.getType().equals(ltdInstanceFromDb.getType())) {
            ltdInstanceFromDb.setType(ltdInstanceDto.getType());
        }

        if (ltdInstanceDto.getDistanceMainOffice() != null &&
                !ltdInstanceDto.getDistanceMainOffice().equals(ltdInstanceFromDb.getDistanceMainOffice())) {
            ltdInstanceFromDb.setDistanceMainOffice(ltdInstanceDto.getDistanceMainOffice());
        }

        if (ltdInstanceDto.getDistanceLocalOffice() != null &&
                !ltdInstanceDto.getDistanceLocalOffice().equals(ltdInstanceFromDb.getDistanceLocalOffice())) {
            ltdInstanceFromDb.setDistanceLocalOffice(ltdInstanceDto.getDistanceLocalOffice());
        }

        if (ltdInstanceDto.getOthers() != null && !ltdInstanceDto.getOthers().equals(ltdInstanceFromDb.getOthers())) {
            ltdInstanceFromDb.setOthers(ltdInstanceDto.getOthers());
        }

        if (ltdInstanceDto.getTelecomCabinet() != null &&
                !ltdInstanceDto.getTelecomCabinet().equals(ltdInstanceFromDb.getTelecomCabinet())) {
            ltdInstanceFromDb.setTelecomCabinet(ltdInstanceDto.getTelecomCabinet());
        }

        if (ltdInstanceDto.getUps() != null && !ltdInstanceDto.getUps().equals(ltdInstanceFromDb.getUps())) {
            ltdInstanceFromDb.setUps(ltdInstanceDto.getUps());
        }

        if (ltdInstanceDto.getServer() != null && !ltdInstanceDto.getServer().equals(ltdInstanceFromDb.getServer())) {
            ltdInstanceFromDb.setServer(ltdInstanceDto.getServer());
        }

        if (ltdInstanceDto.getSwitchs() != null &&
                !ltdInstanceDto.getSwitchs().equals(ltdInstanceFromDb.getSwitchs())) {
            ltdInstanceFromDb.setSwitchs(ltdInstanceDto.getSwitchs());
        }

        if (ltdInstanceDto.getWorkplace() != null &&
                !ltdInstanceDto.getWorkplace().equals(ltdInstanceFromDb.getWorkplace())) {
            ltdInstanceFromDb.setWorkplace(ltdInstanceDto.getWorkplace());
        }

        if (ltdInstanceDto.getEquipment() != null &&
                !ltdInstanceDto.getEquipment().equals(ltdInstanceFromDb.getEquipment())) {
            ltdInstanceFromDb.setEquipment(ltdInstanceDto.getEquipment());
        }

        if (ltdInstanceDto.getStatus() != null &&
                !ltdInstanceDto.getStatus().equals(ltdInstanceFromDb.getStatus())) {
            ltdInstanceFromDb.setStatus(ltdInstanceDto.getStatus());
        }

        if (ltdInstanceDto.getAddress() != null &&
                !ltdInstanceDto.getAddress().equals(ltdInstanceFromDb.getAddress())) {
            ltdInstanceFromDb.setAddress(ltdInstanceDto.getAddress());
        }

        if (ltdInstanceDto.getEmployee() != null &&
                !ltdInstanceDto.getEmployee().getId().equals(ltdInstanceFromDb.getEmployee().getId())) {
            Employee employee = dtoToEntityConverterService.convert(
                    employeeService.findById(ltdInstanceDto.getEmployee().getId()));
            ltdInstanceFromDb.setEmployee(employee);
        }
        ltdInstanceDto.setUpdate(now);
    }
}
