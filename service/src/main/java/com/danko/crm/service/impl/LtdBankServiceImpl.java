package com.danko.crm.service.impl;

import com.danko.crm.model.LtdBank;
import com.danko.crm.model.Status;
import com.danko.crm.repository.LtdBankRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.LtdBankService;
import com.danko.crm.service.LtdService;
import com.danko.crm.service.dto.LtdBankDto;
import com.danko.crm.service.dto.LtdDto;
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
public class LtdBankServiceImpl implements LtdBankService {
    private DtoToEntityConverterService dtoToEntityConverterService;
    private EntityToDtoConverterService entityToDtoConverterService;
    private LtdBankRepository ltdBankRepository;
    private LtdService ltdService;

    @Override
    public LtdBankDto findById(Long id) {
        return entityToDtoConverterService.convert(findEntityById(id));
    }

    private LtdBank findEntityById(Long id) {
        return ltdBankRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES, id));
    }

    @Override
    @Transactional
    public LtdBankDto save(LtdBankDto ltdBankDto) {
        LtdDto ltdDto = ltdService.findById(ltdBankDto.getLtd().getId());
        if (ltdDto.getStatus().equals(Status.ACTIVE)) {
            LocalDateTime now = LocalDateTime.now();

            LtdBank ltdBank = dtoToEntityConverterService.convert(ltdBankDto);
            ltdBank.setCreated(now);
            ltdBank.setUpdate(now);
            ltdBank.setStatus(Status.ACTIVE);
            ltdBank.setLtd(dtoToEntityConverterService.convert(ltdDto));
            return entityToDtoConverterService.convert(ltdBankRepository.save(ltdBank));

        } else {
            throw new NestedEntityInactiveException(EXCEPTION_NESTED_ENTITY_INACTIVE_PROPERTIES);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        LtdBank ltdBank = findEntityById(id);
        ltdBank.setStatus(Status.DELETED);
        ltdBankRepository.save(ltdBank);
    }

    @Override
    public Page<LtdBankDto> findAll(Pageable pageable) {
        return ltdBankRepository.findAll(pageable).map(entityToDtoConverterService::convert);
    }

    @Override
    @Transactional
    public LtdBankDto update(LtdBankDto ltdBankDto) {
        LtdBank ltdBankFromDb = findEntityById(ltdBankDto.getId());
        checkLtdBankBeforeUpdate(ltdBankFromDb, ltdBankDto);
        return entityToDtoConverterService.convert(ltdBankRepository.save(ltdBankFromDb));
    }

    @Override
    public Page<LtdBankDto> findAllByStatus(Pageable pageable, Status status) {
        return ltdBankRepository.findAllByStatus(pageable, status).map(entityToDtoConverterService::convert);
    }

    private void checkLtdBankBeforeUpdate(LtdBank ltdBankFromDb, LtdBankDto ltdBankDto) {
        LocalDateTime now = LocalDateTime.now();
        if (ltdBankDto.getRequisites() != null && !ltdBankDto.getRequisites().equals(ltdBankFromDb.getRequisites())) {
            ltdBankFromDb.setRequisites(ltdBankDto.getRequisites());
        }
        if (ltdBankDto.getStatus() != null && !ltdBankDto.getStatus().equals(ltdBankFromDb.getStatus())) {
            ltdBankFromDb.setStatus(ltdBankDto.getStatus());
        }
        ltdBankFromDb.setUpdate(now);
    }
}
