package com.danko.crm.service.impl;

import com.danko.crm.data_provider.impl.CityEntityAndCityDtoProvider;
import com.danko.crm.data_provider.impl.TicketTypeEntityAndTicketTypeDtoProvider;
import com.danko.crm.model.City;
import com.danko.crm.model.Status;
import com.danko.crm.model.TicketType;
import com.danko.crm.repository.CityRepository;
import com.danko.crm.repository.TicketTypeRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.dto.CityDto;
import com.danko.crm.service.dto.TicketTypeDto;
import com.danko.crm.service.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TicketTypeServiceImplTest {
    private final static Long ID = 1L;

    @InjectMocks
    private TicketTypeServiceImpl ticketTypeService;

    @Mock
    private TicketTypeRepository ticketTypeRepository;

    @Mock
    private DtoToEntityConverterService dtoToEntityConverterService;

    @Mock
    private EntityToDtoConverterService entityToDtoConverterService;

    private final TicketTypeEntityAndTicketTypeDtoProvider ticketTypeEntityAndTicketTypeDtoProvider = new TicketTypeEntityAndTicketTypeDtoProvider();

    @Test
    void findById() {
        TicketTypeDto expected = ticketTypeEntityAndTicketTypeDtoProvider.getDtoWithId();
        TicketType ticketTypeFromDb = ticketTypeEntityAndTicketTypeDtoProvider.getEntityWithId();
        Mockito.when(ticketTypeRepository.findById(ID)).thenReturn(Optional.of(ticketTypeFromDb));
        Mockito.when(entityToDtoConverterService.convert(ticketTypeFromDb)).thenReturn(expected);

        TicketTypeDto actual = ticketTypeService.findById(ID);

        assertEquals(expected, actual);
    }

    @Test
    void findByIdException() {
        Mockito.when(ticketTypeRepository.findById(ID)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> ticketTypeService.findById(ID));
        assertEquals(ID, exception.getId());
    }

    @Test
    void save() {
        TicketTypeDto expected = ticketTypeEntityAndTicketTypeDtoProvider.getDtoWithId();
        TicketType ticketTypeFromDb = ticketTypeEntityAndTicketTypeDtoProvider.getEntityWithId();

        Mockito.when(entityToDtoConverterService.convert(ticketTypeFromDb)).thenReturn(expected);
        Mockito.when(dtoToEntityConverterService.convert(expected)).thenReturn(ticketTypeFromDb);
        Mockito.when(ticketTypeRepository.save(ticketTypeFromDb)).thenReturn(ticketTypeFromDb);

        TicketTypeDto actual = ticketTypeService.save(expected);

        assertEquals(expected, actual);
    }

    @Test
    void saveFindInDataBase() {
        TicketTypeDto expected = ticketTypeEntityAndTicketTypeDtoProvider.getDtoWithId();
        TicketType ticketTypeFromDb = ticketTypeEntityAndTicketTypeDtoProvider.getEntityWithId();

        Mockito.when(ticketTypeRepository.findByName(expected.getName())).thenReturn(Optional.of(ticketTypeFromDb));
        Mockito.when(entityToDtoConverterService.convert(ticketTypeFromDb)).thenReturn(expected);

        TicketTypeDto actual = ticketTypeService.save(expected);

        assertEquals(expected, actual);
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<TicketType> ticketTypes = new PageImpl<>(Arrays.asList(ticketTypeEntityAndTicketTypeDtoProvider.getEntityWithId()));
        Page<TicketTypeDto> expected = new PageImpl<>(Arrays.asList(ticketTypeEntityAndTicketTypeDtoProvider.getDtoWithId()));

        Mockito.when(ticketTypeRepository.findAll(pageable)).thenReturn(ticketTypes);
        Mockito.when(entityToDtoConverterService.convert(ticketTypeEntityAndTicketTypeDtoProvider.getEntityWithId()))
                .thenReturn(ticketTypeEntityAndTicketTypeDtoProvider.getDtoWithId());

        Page<TicketTypeDto> actual = ticketTypeService.findAll(pageable);

        assertEquals(expected, actual);
    }

    @Test
    void findAllByStatus() {
        Status status = Status.NOT_ACTIVE;
        Pageable pageable = PageRequest.of(0, 10);

        Page<TicketType> ticketTypes = new PageImpl<>(
                Arrays.asList(ticketTypeEntityAndTicketTypeDtoProvider.getEntityWithIdNotActive()));
        Page<TicketTypeDto> expected = new PageImpl<>(
                Arrays.asList(ticketTypeEntityAndTicketTypeDtoProvider.getDtoWithIdNotActive()));

        Mockito.when(ticketTypeRepository.findAllByStatus(pageable, status)).thenReturn(ticketTypes);
        Mockito.when(entityToDtoConverterService.convert(ticketTypeEntityAndTicketTypeDtoProvider.getEntityWithIdNotActive()))
                .thenReturn(ticketTypeEntityAndTicketTypeDtoProvider.getDtoWithIdNotActive());

        Page<TicketTypeDto> actual = ticketTypeService.findAllByStatus(pageable, status);

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        String newTicketTypeName = "new ticket type name";
        Integer newTicketTypeAction = -1;
        Integer newTicketTypePriority = 60;
        Status newStatus = Status.NOT_ACTIVE;

        TicketType ticketTypeFromDb = ticketTypeEntityAndTicketTypeDtoProvider.getEntityWithId();

        TicketType ticketTypeFromDbAfterUpdate = ticketTypeEntityAndTicketTypeDtoProvider.getEntityWithId();
        ticketTypeFromDbAfterUpdate.setName(newTicketTypeName);
        ticketTypeFromDbAfterUpdate.setStatus(newStatus);
        ticketTypeFromDbAfterUpdate.setPriority(newTicketTypePriority);
        ticketTypeFromDbAfterUpdate.setAction(newTicketTypeAction);

        TicketTypeDto expected = ticketTypeEntityAndTicketTypeDtoProvider.getDtoWithId();
        expected.setName(newTicketTypeName);
        expected.setStatus(newStatus);
        expected.setPriority(newTicketTypePriority);
        expected.setAction(newTicketTypeAction);

        TicketTypeDto updatedTicketTypeDto = ticketTypeEntityAndTicketTypeDtoProvider.getDtoWithId();
        updatedTicketTypeDto.setName(newTicketTypeName);
        updatedTicketTypeDto.setStatus(newStatus);
        updatedTicketTypeDto.setPriority(newTicketTypePriority);
        updatedTicketTypeDto.setAction(newTicketTypeAction);


        Mockito.when(ticketTypeRepository.findById(ID)).thenReturn(Optional.of(ticketTypeFromDb));
        Mockito.when(ticketTypeRepository.save(ticketTypeFromDb)).thenReturn(ticketTypeFromDbAfterUpdate);
        Mockito.when(entityToDtoConverterService.convert(ticketTypeFromDbAfterUpdate)).thenReturn(updatedTicketTypeDto);

        TicketTypeDto actual = ticketTypeService.update(updatedTicketTypeDto);

        assertEquals(expected, actual);
    }

    @Test
    void updateFindByIdException() {
        TicketTypeDto ticketTypeDto= ticketTypeEntityAndTicketTypeDtoProvider.getDtoWithId();

        Mockito.when(ticketTypeRepository.findById(ticketTypeDto.getId())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> ticketTypeService.update(ticketTypeDto));

        assertEquals(ID, exception.getId());
    }
}
