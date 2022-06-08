package com.danko.crm.service.impl;

import com.danko.crm.model.Ltd;
import com.danko.crm.model.Status;
import com.danko.crm.repository.LtdRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.LtdService;
import com.danko.crm.service.dto.LtdDto;
import com.danko.crm.service.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.danko.crm.service.constant.ExceptionErrorMessages.EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES;

@Service
@AllArgsConstructor
public class LtdServiceImpl implements LtdService {
    private DtoToEntityConverterService dtoToEntityConverterService;
    private EntityToDtoConverterService entityToDtoConverterService;
    private LtdRepository ltdRepository;

    @Override
    public LtdDto findById(Long id) {
        return entityToDtoConverterService.convert(findEntityById(id));
    }

    private Ltd findEntityById(Long id) {
        return ltdRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES, id));
    }

    @Override
    @Transactional
    public LtdDto save(LtdDto ltdDto) {
        Optional<Ltd> optionalLtd = ltdRepository.findByNameFull(ltdDto.getNameFull());

        if (optionalLtd.isPresent()) {
            return entityToDtoConverterService.convert(optionalLtd.get());
        } else {
            LocalDateTime now = LocalDateTime.now();
            ltdDto.setCreated(now);
            ltdDto.setUpdate(now);
            ltdDto.setStatus(Status.ACTIVE);
            return entityToDtoConverterService.convert(ltdRepository.save(dtoToEntityConverterService.convert(ltdDto)));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Ltd ltd = findEntityById(id);
        ltd.setStatus(Status.DELETED);
        ltdRepository.save(ltd);
    }

    @Override
    public Page<LtdDto> findAll(Pageable pageable) {
        return ltdRepository.findAll(pageable).map(entityToDtoConverterService::convert);
    }

    @Override
    public LtdDto update(LtdDto ltdDto) {
        Ltd ltdFromDb = findEntityById(ltdDto.getId());
        checkLtdContractBeforeUpdate(ltdFromDb, ltdDto);
        return entityToDtoConverterService.convert(ltdRepository.save(ltdFromDb));
    }

    @Override
    public Page<LtdDto> findAllByStatus(Pageable pageable, Status status) {
        return ltdRepository.findAllByStatus(pageable, status).map(entityToDtoConverterService::convert);
    }

    private void checkLtdContractBeforeUpdate(Ltd ltdFromDb, LtdDto ltdDto) {
        LocalDateTime now = LocalDateTime.now();

        if (ltdDto.getNameFull() != null && !ltdDto.getNameFull().equals(ltdFromDb.getNameFull())) {
            ltdFromDb.setNameFull(ltdDto.getNameFull());
        }

        if (ltdDto.getNameShort() != null && !ltdDto.getNameShort().equals(ltdFromDb.getNameShort())) {
            ltdFromDb.setNameShort(ltdDto.getNameShort());
        }

        if (ltdDto.getAddress() != null && !ltdDto.getAddress().equals(ltdFromDb.getAddress())) {
            ltdFromDb.setAddress(ltdDto.getAddress());
        }

        if (ltdDto.getUnp() != null && !ltdDto.getUnp().equals(ltdFromDb.getUnp())) {
            ltdFromDb.setUnp(ltdDto.getUnp());
        }

        if (ltdDto.getStatus() != null && !ltdDto.getStatus().equals(ltdFromDb.getStatus())) {
            ltdFromDb.setStatus(ltdDto.getStatus());
        }

        ltdFromDb.setUpdate(now);
    }
}
