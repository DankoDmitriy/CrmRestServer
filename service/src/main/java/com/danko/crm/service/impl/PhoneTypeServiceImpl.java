package com.danko.crm.service.impl;

import com.danko.crm.model.PhoneType;
import com.danko.crm.model.Status;
import com.danko.crm.repository.PhoneTypeRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.PhoneTypeService;
import com.danko.crm.service.dto.PhoneTypeDto;
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
public class PhoneTypeServiceImpl implements PhoneTypeService {
    private PhoneTypeRepository phoneTypeRepository;
    private DtoToEntityConverterService dtoToEntityConverterService;
    private EntityToDtoConverterService entityToDtoConverterService;

    @Override
    public PhoneTypeDto findById(Long id) {
        return entityToDtoConverterService.convert(findEntityById(id));
    }

    private PhoneType findEntityById(Long id) {
        return phoneTypeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES, id));
    }

    @Override
    public PhoneTypeDto save(PhoneTypeDto phoneTypeDto) {
        Optional<PhoneType> optionalPhoneType = phoneTypeRepository.findByName(phoneTypeDto.getName());
        if (optionalPhoneType.isPresent()) {
            return entityToDtoConverterService.convert(optionalPhoneType.get());
        } else {
            LocalDateTime now = LocalDateTime.now();
            phoneTypeDto.setCreated(now);
            phoneTypeDto.setUpdate(now);
            phoneTypeDto.setStatus(Status.ACTIVE);
            return entityToDtoConverterService.convert(
                    phoneTypeRepository.save(
                            dtoToEntityConverterService.convert(phoneTypeDto)
                    ));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        PhoneType phoneType = findEntityById(id);
        phoneType.setStatus(Status.DELETED);
        phoneTypeRepository.save(phoneType);
    }

    @Override
    public Page<PhoneTypeDto> findAll(Pageable pageable) {
        return phoneTypeRepository.findAll(pageable).map(entityToDtoConverterService::convert);
    }

    @Override
    public Page<PhoneTypeDto> findAllByStatus(Pageable pageable, Status status) {
        return phoneTypeRepository.findAllByStatus(pageable, status).map(entityToDtoConverterService::convert);
    }

    @Override
    @Transactional
    public PhoneTypeDto update(PhoneTypeDto phoneTypeDto) {
        PhoneType typeFromDb = findEntityById(phoneTypeDto.getId());
        checkTypeBeforeUpdate(typeFromDb, phoneTypeDto);
        return entityToDtoConverterService.convert(phoneTypeRepository.save(typeFromDb));
    }

    private void checkTypeBeforeUpdate(PhoneType typeFromDb, PhoneTypeDto phoneTypeDto) {
        LocalDateTime now = LocalDateTime.now();

        if (phoneTypeDto.getName() != null && !phoneTypeDto.getName().equals(typeFromDb.getName())) {
            typeFromDb.setName(phoneTypeDto.getName());
        }
        if (phoneTypeDto.getStatus() != null && !phoneTypeDto.getStatus().equals(typeFromDb.getStatus())) {
            typeFromDb.setStatus(phoneTypeDto.getStatus());
        }
        typeFromDb.setUpdate(now);
    }
}
