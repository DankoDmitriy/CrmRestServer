package com.danko.crm.service.impl;

import com.danko.crm.model.LtdContract;
import com.danko.crm.model.Status;
import com.danko.crm.repository.LtdContractRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.LtdContractService;
import com.danko.crm.service.LtdService;
import com.danko.crm.service.dto.LtdContractDto;
import com.danko.crm.service.dto.LtdDto;
import com.danko.crm.service.exception.EntityNotFoundException;
import com.danko.crm.service.exception.NestedEntityInactiveException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES;
import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_NESTED_ENTITY_INACTIVE_PROPERTIES;

@Service
@AllArgsConstructor
public class LtdContractServiceImpl implements LtdContractService {
    private DtoToEntityConverterService dtoToEntityConverterService;
    private EntityToDtoConverterService entityToDtoConverterService;
    private LtdContractRepository ltdContractRepository;
    private LtdService ltdService;

    @Override
    public LtdContractDto findById(Long id) {
        return entityToDtoConverterService.convert(findEntityById(id));
    }

    private LtdContract findEntityById(Long id) {
        return ltdContractRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES, id));
    }

    @Override
    @Transactional
    public LtdContractDto save(LtdContractDto ltdContractDto) {
        Optional<LtdContract> optionalLtdContract = ltdContractRepository.findByNumber(ltdContractDto.getNumber());

        if (optionalLtdContract.isPresent()) {
            return entityToDtoConverterService.convert(optionalLtdContract.get());
        } else {
            LtdDto ltdDto = ltdService.findById(ltdContractDto.getLtd().getId());
            if (ltdDto.getStatus().equals(Status.ACTIVE)) {
                LocalDateTime now = LocalDateTime.now();

                LtdContract ltdContract = dtoToEntityConverterService.convert(ltdContractDto);
                ltdContract.setCreated(now);
                ltdContract.setUpdate(now);
                ltdContract.setStatus(Status.ACTIVE);
                ltdContract.setLtd(dtoToEntityConverterService.convert(ltdDto));

                return entityToDtoConverterService.convert(ltdContractRepository.save(ltdContract));
            } else {
                throw new NestedEntityInactiveException(EXCEPTION_NESTED_ENTITY_INACTIVE_PROPERTIES);
            }
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        LtdContract ltdContract = findEntityById(id);
        ltdContract.setStatus(Status.DELETED);
        ltdContractRepository.save(ltdContract);
    }

    @Override
    public Page<LtdContractDto> findAll(Pageable pageable) {
        return ltdContractRepository.findAll(pageable).map(entityToDtoConverterService::convert);
    }

    @Override
    @Transactional
    public LtdContractDto update(LtdContractDto ltdContractDto) {
        LtdContract ltdContractFromDb = findEntityById(ltdContractDto.getId());
        checkLtdContractBeforeUpdate(ltdContractFromDb, ltdContractDto);
        return entityToDtoConverterService.convert(ltdContractRepository.save(ltdContractFromDb));
    }

    @Override
    public Page<LtdContractDto> findAllByStatus(Pageable pageable, Status status) {
        return ltdContractRepository.findAllByStatus(pageable, status).map(entityToDtoConverterService::convert);
    }

    private void checkLtdContractBeforeUpdate(LtdContract ltdContractFromDb, LtdContractDto ltdContractDto) {
        LocalDateTime now = LocalDateTime.now();
        if (ltdContractDto.getContractStart() != null &&
                !ltdContractDto.getContractStart().equals(ltdContractFromDb.getContractStart())) {
            ltdContractFromDb.setContractStart(ltdContractDto.getContractStart());
        }
        if (ltdContractDto.getNumber() != null &&
                !ltdContractDto.getNumber().equals(ltdContractFromDb.getNumber())) {
            ltdContractFromDb.setNumber(ltdContractDto.getNumber());
        }
        if (ltdContractDto.getOther() != null &&
                !ltdContractDto.getOther().equals(ltdContractFromDb.getOther())) {
            ltdContractFromDb.setOther(ltdContractDto.getOther());
        }
        if (ltdContractDto.getStatus() != null &&
                !ltdContractDto.getStatus().equals(ltdContractFromDb.getStatus())) {
            ltdContractFromDb.setStatus(ltdContractDto.getStatus());
        }
        ltdContractFromDb.setUpdate(now);
    }
}
