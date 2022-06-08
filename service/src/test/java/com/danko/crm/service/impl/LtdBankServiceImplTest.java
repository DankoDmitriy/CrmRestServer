package com.danko.crm.service.impl;

import com.danko.crm.data_provider.impl.LtdBankEntityAndLtdBankDtoProvider;
import com.danko.crm.model.Car;
import com.danko.crm.model.LtdBank;
import com.danko.crm.model.Status;
import com.danko.crm.repository.LtdBankRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.LtdService;
import com.danko.crm.service.dto.CarDto;
import com.danko.crm.service.dto.LtdBankDto;
import com.danko.crm.service.exception.EntityNotFoundException;
import com.danko.crm.service.exception.NestedEntityInactiveException;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LtdBankServiceImplTest {
    private final static Long ID = 1L;

    @InjectMocks
    private LtdBankServiceImpl ltdBankService;

    @Mock
    private DtoToEntityConverterService dtoToEntityConverterService;

    @Mock
    private EntityToDtoConverterService entityToDtoConverterService;

    @Mock
    private LtdBankRepository ltdBankRepository;

    @Mock
    private LtdService ltdService;

    private final LtdBankEntityAndLtdBankDtoProvider ltdBankEntityAndLtdBankDtoProvider = new LtdBankEntityAndLtdBankDtoProvider();

    @Test
    void findById() {
        LtdBankDto expected = ltdBankEntityAndLtdBankDtoProvider.getDtoWithId();
        LtdBank ltdBankFromDb = ltdBankEntityAndLtdBankDtoProvider.getEntityWithId();

        Mockito.when(ltdBankRepository.findById(expected.getId())).thenReturn(Optional.of(ltdBankFromDb));

        Mockito.when(entityToDtoConverterService.convert(ltdBankFromDb)).thenReturn(expected);

        LtdBankDto actual = ltdBankService.findById(expected.getId());

        assertEquals(expected, actual);
    }

    @Test
    void findByIdException() {
        Mockito.when(ltdBankRepository.findById(ID)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> ltdBankService.findById(ID));
        assertEquals(ID, exception.getId());
    }

    @Test
    void save() {
        LtdBankDto expected = ltdBankEntityAndLtdBankDtoProvider.getDtoWithId();
        LtdBank ltdBankFromDb = ltdBankEntityAndLtdBankDtoProvider.getEntityWithId();

        Mockito.when(entityToDtoConverterService.convert(ltdBankFromDb)).thenReturn(expected);
        Mockito.when(dtoToEntityConverterService.convert(expected)).thenReturn(ltdBankFromDb);
        Mockito.when(ltdBankRepository.save(ltdBankFromDb)).thenReturn(ltdBankFromDb);
        Mockito.when(ltdService.findById(ltdBankEntityAndLtdBankDtoProvider.getDtoWithId().getLtd().getId()))
                .thenReturn(ltdBankEntityAndLtdBankDtoProvider.getDtoWithId().getLtd());

        LtdBankDto actual = ltdBankService.save(expected);

        assertEquals(expected, actual);
    }

    @Test()
    void saveException() {
        LtdBankDto expected = ltdBankEntityAndLtdBankDtoProvider.getDtoWithId();
        expected.getLtd().setStatus(Status.NOT_ACTIVE);

        Mockito.when(ltdService.findById(expected.getLtd().getId()))
                .thenReturn(expected.getLtd());

        NestedEntityInactiveException exception = assertThrows(NestedEntityInactiveException.class, () -> {
            ltdBankService.save(expected);
        });
        assertNotNull(exception.getErrorMessage());
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<LtdBank> ltdBanks = new PageImpl<>(Arrays.asList(ltdBankEntityAndLtdBankDtoProvider.getEntityWithId()));
        Page<LtdBankDto> expected = new PageImpl<>(Arrays.asList(ltdBankEntityAndLtdBankDtoProvider.getDtoWithId()));

        Mockito.when(ltdBankRepository.findAll(pageable)).thenReturn(ltdBanks);
        Mockito.when(entityToDtoConverterService.convert(ltdBankEntityAndLtdBankDtoProvider.getEntityWithId()))
                .thenReturn(ltdBankEntityAndLtdBankDtoProvider.getDtoWithId());

        Page<LtdBankDto> actual = ltdBankService.findAll(pageable);

        assertEquals(expected, actual);
    }

    @Test
    void findAllByStatus() {
        Status status = Status.NOT_ACTIVE;
        Pageable pageable = PageRequest.of(0, 10);

        Page<LtdBank> ltdBanks = new PageImpl<>(Arrays.asList(ltdBankEntityAndLtdBankDtoProvider.getEntityWithIdNotActive()));
        Page<LtdBankDto> expected = new PageImpl<>(Arrays.asList(ltdBankEntityAndLtdBankDtoProvider.getDtoWithIdNotActive()));

        Mockito.when(ltdBankRepository.findAllByStatus(pageable, status)).thenReturn(ltdBanks);
        Mockito.when(entityToDtoConverterService.convert(ltdBankEntityAndLtdBankDtoProvider.getEntityWithIdNotActive()))
                .thenReturn(ltdBankEntityAndLtdBankDtoProvider.getDtoWithIdNotActive());

        Page<LtdBankDto> actual = ltdBankService.findAllByStatus(pageable, status);

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        String newRequisites = "new requisites";
        Status newStatus = Status.NOT_ACTIVE;

        LtdBank ltdBankFromDb = ltdBankEntityAndLtdBankDtoProvider.getEntityWithId();

        LtdBank ltdBankFromDbAfterUpdate = ltdBankEntityAndLtdBankDtoProvider.getEntityWithId();
        ltdBankFromDbAfterUpdate.setRequisites(newRequisites);
        ltdBankFromDbAfterUpdate.setStatus(newStatus);

        LtdBankDto expected = ltdBankEntityAndLtdBankDtoProvider.getDtoWithId();
        expected.setRequisites(newRequisites);
        expected.setStatus(newStatus);

        LtdBankDto updatedLtdBankDto = ltdBankEntityAndLtdBankDtoProvider.getDtoWithId();
        updatedLtdBankDto.setRequisites(newRequisites);
        updatedLtdBankDto.setStatus(newStatus);

        Mockito.when(ltdBankRepository.findById(ID)).thenReturn(Optional.of(ltdBankFromDb));
        Mockito.when(ltdBankRepository.save(ltdBankFromDb)).thenReturn(ltdBankFromDbAfterUpdate);
        Mockito.when(entityToDtoConverterService.convert(ltdBankFromDbAfterUpdate)).thenReturn(updatedLtdBankDto);

        LtdBankDto actual = ltdBankService.update(updatedLtdBankDto);

        assertEquals(expected, actual);
    }

    @Test
    void updateFindByIdException() {
        LtdBankDto ltdBankDto = ltdBankEntityAndLtdBankDtoProvider.getDtoWithId();

        Mockito.when(ltdBankRepository.findById(ltdBankDto.getId())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> ltdBankService.update(ltdBankDto));

        assertEquals(ID, exception.getId());
    }
}
