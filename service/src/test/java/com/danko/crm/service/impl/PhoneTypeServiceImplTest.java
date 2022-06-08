package com.danko.crm.service.impl;

import com.danko.crm.data_provider.impl.PhoneTypeEntityAndPhoneTypeDtoProvider;
import com.danko.crm.model.PhoneType;
import com.danko.crm.model.Status;
import com.danko.crm.repository.PhoneTypeRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.dto.PhoneTypeDto;
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
class PhoneTypeServiceImplTest {
    private final static Long ID = 1L;

    @InjectMocks
    private PhoneTypeServiceImpl phoneTypeService;

    @Mock
    private PhoneTypeRepository phoneTypeRepository;

    @Mock
    private DtoToEntityConverterService dtoToEntityConverterService;

    @Mock
    private EntityToDtoConverterService entityToDtoConverterService;

    private final PhoneTypeEntityAndPhoneTypeDtoProvider phoneTypeEntityAndPhoneTypeDtoProvider = new PhoneTypeEntityAndPhoneTypeDtoProvider();

    @Test
    void findById() {
        PhoneTypeDto expected = phoneTypeEntityAndPhoneTypeDtoProvider.getDtoWithId();
        PhoneType phoneTypeFromDb = phoneTypeEntityAndPhoneTypeDtoProvider.getEntityWithId();
        Mockito.when(phoneTypeRepository.findById(ID)).thenReturn(Optional.of(phoneTypeFromDb));
        Mockito.when(entityToDtoConverterService.convert(phoneTypeFromDb)).thenReturn(expected);

        PhoneTypeDto actual = phoneTypeService.findById(ID);

        assertEquals(expected, actual);
    }

    @Test
    void findByIdException() {
        Mockito.when(phoneTypeRepository.findById(ID)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> phoneTypeService.findById(ID));
        assertEquals(ID, exception.getId());
    }

    @Test
    void save() {
        PhoneTypeDto expected = phoneTypeEntityAndPhoneTypeDtoProvider.getDtoWithId();
        PhoneType phoneTypeFromDb = phoneTypeEntityAndPhoneTypeDtoProvider.getEntityWithId();

        Mockito.when(entityToDtoConverterService.convert(phoneTypeFromDb)).thenReturn(expected);
        Mockito.when(dtoToEntityConverterService.convert(expected)).thenReturn(phoneTypeFromDb);
        Mockito.when(phoneTypeRepository.save(phoneTypeFromDb)).thenReturn(phoneTypeFromDb);

        PhoneTypeDto actual = phoneTypeService.save(expected);

        assertEquals(expected, actual);
    }

    @Test
    void saveFindInDataBase() {
        PhoneTypeDto expected = phoneTypeEntityAndPhoneTypeDtoProvider.getDtoWithId();
        PhoneType phoneTypeFromDb = phoneTypeEntityAndPhoneTypeDtoProvider.getEntityWithId();

        Mockito.when(phoneTypeRepository.findByName(expected.getName())).thenReturn(Optional.of(phoneTypeFromDb));
        Mockito.when(entityToDtoConverterService.convert(phoneTypeFromDb)).thenReturn(expected);

        PhoneTypeDto actual = phoneTypeService.save(expected);

        assertEquals(expected, actual);
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<PhoneType> phoneTypes = new PageImpl<>(Arrays.asList(phoneTypeEntityAndPhoneTypeDtoProvider.getEntityWithId()));
        Page<PhoneTypeDto> expected = new PageImpl<>(Arrays.asList(phoneTypeEntityAndPhoneTypeDtoProvider.getDtoWithId()));

        Mockito.when(phoneTypeRepository.findAll(pageable)).thenReturn(phoneTypes);
        Mockito.when(entityToDtoConverterService.convert(phoneTypeEntityAndPhoneTypeDtoProvider.getEntityWithId()))
                .thenReturn(phoneTypeEntityAndPhoneTypeDtoProvider.getDtoWithId());

        Page<PhoneTypeDto> actual = phoneTypeService.findAll(pageable);

        assertEquals(expected, actual);
    }

    @Test
    void findAllByStatus() {
        Status status = Status.NOT_ACTIVE;
        Pageable pageable = PageRequest.of(0, 10);

        Page<PhoneType> phoneTypes = new PageImpl<>(Arrays.asList(phoneTypeEntityAndPhoneTypeDtoProvider.getEntityWithIdNotActive()));
        Page<PhoneTypeDto> expected = new PageImpl<>(Arrays.asList(phoneTypeEntityAndPhoneTypeDtoProvider.getDtoWithIdNotActive()));

        Mockito.when(phoneTypeRepository.findAllByStatus(pageable, status)).thenReturn(phoneTypes);
        Mockito.when(entityToDtoConverterService.convert(phoneTypeEntityAndPhoneTypeDtoProvider.getEntityWithIdNotActive()))
                .thenReturn(phoneTypeEntityAndPhoneTypeDtoProvider.getDtoWithIdNotActive());

        Page<PhoneTypeDto> actual = phoneTypeService.findAllByStatus(pageable, status);

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        String newPhoneTypeName = "new phone type name";
        Status newStatus = Status.NOT_ACTIVE;

        PhoneType cityFromDb = phoneTypeEntityAndPhoneTypeDtoProvider.getEntityWithId();

        PhoneType PhoneTypeFromDbAfterUpdate = phoneTypeEntityAndPhoneTypeDtoProvider.getEntityWithId();
        PhoneTypeFromDbAfterUpdate.setName(newPhoneTypeName);
        PhoneTypeFromDbAfterUpdate.setStatus(newStatus);

        PhoneTypeDto expected = phoneTypeEntityAndPhoneTypeDtoProvider.getDtoWithId();
        expected.setName(newPhoneTypeName);
        expected.setStatus(newStatus);

        PhoneTypeDto updatedPhoneTypeDto = phoneTypeEntityAndPhoneTypeDtoProvider.getDtoWithId();
        updatedPhoneTypeDto.setName(newPhoneTypeName);
        updatedPhoneTypeDto.setStatus(newStatus);

        Mockito.when(phoneTypeRepository.findById(ID)).thenReturn(Optional.of(cityFromDb));
        Mockito.when(phoneTypeRepository.save(cityFromDb)).thenReturn(PhoneTypeFromDbAfterUpdate);
        Mockito.when(entityToDtoConverterService.convert(PhoneTypeFromDbAfterUpdate)).thenReturn(updatedPhoneTypeDto);

        PhoneTypeDto actual = phoneTypeService.update(updatedPhoneTypeDto);

        assertEquals(expected, actual);
    }

    @Test
    void updateFindByIdException() {
        PhoneTypeDto phoneTypeDto = phoneTypeEntityAndPhoneTypeDtoProvider.getDtoWithId();

        Mockito.when(phoneTypeRepository.findById(phoneTypeDto.getId())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> phoneTypeService.update(phoneTypeDto));

        assertEquals(ID, exception.getId());
    }
}
