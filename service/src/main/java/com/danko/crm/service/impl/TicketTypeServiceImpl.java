package com.danko.crm.service.impl;

import com.danko.crm.model.Status;
import com.danko.crm.model.TicketType;
import com.danko.crm.repository.TicketTypeRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.TicketTypeService;
import com.danko.crm.service.dto.TicketTypeDto;
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
public class TicketTypeServiceImpl implements TicketTypeService {
    private DtoToEntityConverterService dtoToEntityConverterService;
    private EntityToDtoConverterService entityToDtoConverterService;
    private TicketTypeRepository ticketTypeRepository;

    @Override
    public TicketTypeDto findById(Long id) {
        return entityToDtoConverterService.convert(findEntityById(id));
    }

    private TicketType findEntityById(Long id) {
        return ticketTypeRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES, id));
    }

    @Override
    @Transactional
    public TicketTypeDto save(TicketTypeDto ticketTypeDto) {
        Optional<TicketType> optionalTicketType = ticketTypeRepository.findByName(ticketTypeDto.getName());

        if (optionalTicketType.isPresent()) {
            return entityToDtoConverterService.convert(optionalTicketType.get());
        } else {
            LocalDateTime now = LocalDateTime.now();
            ticketTypeDto.setCreated(now);
            ticketTypeDto.setUpdate(now);
            ticketTypeDto.setStatus(Status.ACTIVE);
            return entityToDtoConverterService.convert(
                    ticketTypeRepository.save(
                            dtoToEntityConverterService.convert(ticketTypeDto)));
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        TicketType ticketType = findEntityById(id);
        ticketType.setStatus(Status.DELETED);
        ticketTypeRepository.save(ticketType);
    }

    @Override
    public Page<TicketTypeDto> findAll(Pageable pageable) {
        return ticketTypeRepository.findAll(pageable).map(entityToDtoConverterService::convert);
    }

    @Override
    public TicketTypeDto update(TicketTypeDto ticketTypeDto) {
        TicketType ticketTypeFromDb = findEntityById(ticketTypeDto.getId());
        checkTicketTypeBeforeUpdate(ticketTypeFromDb, ticketTypeDto);
        return entityToDtoConverterService.convert(ticketTypeRepository.save(ticketTypeFromDb));
    }

    @Override
    public Page<TicketTypeDto> findAllByStatus(Pageable pageable, Status status) {
        return ticketTypeRepository.findAllByStatus(pageable, status).map(entityToDtoConverterService::convert);
    }

    private void checkTicketTypeBeforeUpdate(TicketType ticketTypeFromDb, TicketTypeDto ticketTypeDto) {
        LocalDateTime now = LocalDateTime.now();
        if (ticketTypeDto.getName() != null && !ticketTypeDto.getName().equals(ticketTypeFromDb.getName())) {
            ticketTypeFromDb.setName(ticketTypeDto.getName());
        }
        if (ticketTypeDto.getAction() != null && !ticketTypeDto.getAction().equals(ticketTypeFromDb.getAction())) {
            ticketTypeFromDb.setAction(ticketTypeDto.getAction());
        }
        if (ticketTypeDto.getPriority() != null && !ticketTypeDto.getPriority().equals(ticketTypeFromDb.getPriority())) {
            ticketTypeFromDb.setPriority(ticketTypeDto.getPriority());
        }
        if (ticketTypeDto.getStatus() != null && !ticketTypeDto.getStatus().equals(ticketTypeFromDb.getStatus())) {
            ticketTypeFromDb.setStatus(ticketTypeDto.getStatus());
        }
        ticketTypeFromDb.setUpdate(now);
    }
}

