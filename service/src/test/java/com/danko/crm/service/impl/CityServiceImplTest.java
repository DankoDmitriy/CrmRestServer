package com.danko.crm.service.impl;

import com.danko.crm.data_provider.impl.CityEntityAndCityDtoProvider;
import com.danko.crm.model.City;
import com.danko.crm.model.Status;
import com.danko.crm.repository.CityRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.dto.CityDto;
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
class CityServiceImplTest {
    private final static Long ID = 1L;

    @InjectMocks
    private CityServiceImpl cityService;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private DtoToEntityConverterService dtoToEntityConverterService;

    @Mock
    private EntityToDtoConverterService entityToDtoConverterService;

    private final CityEntityAndCityDtoProvider cityEntityAndCityDtoProvider = new CityEntityAndCityDtoProvider();

    @Test
    void findById() {
        CityDto expected = cityEntityAndCityDtoProvider.getDtoWithId();
        City cityFromDb = cityEntityAndCityDtoProvider.getEntityWithId();
        Mockito.when(cityRepository.findById(ID)).thenReturn(Optional.of(cityFromDb));
        Mockito.when(entityToDtoConverterService.convert(cityFromDb)).thenReturn(expected);

        CityDto actual = cityService.findById(ID);

        assertEquals(expected, actual);
    }

    @Test
    void findByIdException() {
        Mockito.when(cityRepository.findById(ID)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> cityService.findById(ID));
        assertEquals(ID, exception.getId());
    }

    @Test
    void save() {
        CityDto expected = cityEntityAndCityDtoProvider.getDtoWithId();
        City cityFromDb = cityEntityAndCityDtoProvider.getEntityWithId();

        Mockito.when(entityToDtoConverterService.convert(cityFromDb)).thenReturn(expected);
        Mockito.when(dtoToEntityConverterService.convert(expected)).thenReturn(cityFromDb);
        Mockito.when(cityRepository.save(cityFromDb)).thenReturn(cityFromDb);

        CityDto actual = cityService.save(expected);

        assertEquals(expected, actual);
    }

    @Test
    void saveFindInDataBase() {
        CityDto expected = cityEntityAndCityDtoProvider.getDtoWithId();
        City cityFromDb = cityEntityAndCityDtoProvider.getEntityWithId();

        Mockito.when(cityRepository.findByName(expected.getName())).thenReturn(Optional.of(cityFromDb));
        Mockito.when(entityToDtoConverterService.convert(cityFromDb)).thenReturn(expected);

        CityDto actual = cityService.save(expected);

        assertEquals(expected, actual);
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<City> cities = new PageImpl<>(Arrays.asList(cityEntityAndCityDtoProvider.getEntityWithId()));
        Page<CityDto> expected = new PageImpl<>(Arrays.asList(cityEntityAndCityDtoProvider.getDtoWithId()));

        Mockito.when(cityRepository.findAll(pageable)).thenReturn(cities);
        Mockito.when(entityToDtoConverterService.convert(cityEntityAndCityDtoProvider.getEntityWithId()))
                .thenReturn(cityEntityAndCityDtoProvider.getDtoWithId());

        Page<CityDto> actual = cityService.findAll(pageable);

        assertEquals(expected, actual);
    }

    @Test
    void findAllByStatus() {
        Status status = Status.NOT_ACTIVE;
        Pageable pageable = PageRequest.of(0, 10);

        Page<City> cities = new PageImpl<>(Arrays.asList(cityEntityAndCityDtoProvider.getEntityWithIdNotActive()));
        Page<CityDto> expected = new PageImpl<>(Arrays.asList(cityEntityAndCityDtoProvider.getDtoWithIdNotActive()));

        Mockito.when(cityRepository.findAllByStatus(pageable, status)).thenReturn(cities);
        Mockito.when(entityToDtoConverterService.convert(cityEntityAndCityDtoProvider.getEntityWithIdNotActive()))
                .thenReturn(cityEntityAndCityDtoProvider.getDtoWithIdNotActive());

        Page<CityDto> actual = cityService.findAllByStatus(pageable, status);

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        String newCityName = "new city name";
        Status newStatus = Status.NOT_ACTIVE;

        City cityFromDb = cityEntityAndCityDtoProvider.getEntityWithId();

        City cityFromDbAfterUpdate = cityEntityAndCityDtoProvider.getEntityWithId();
        cityFromDbAfterUpdate.setName(newCityName);
        cityFromDbAfterUpdate.setStatus(newStatus);

        CityDto expected = cityEntityAndCityDtoProvider.getDtoWithId();
        expected.setName(newCityName);
        expected.setStatus(newStatus);

        CityDto updatedCityDto = cityEntityAndCityDtoProvider.getDtoWithId();
        updatedCityDto.setName(newCityName);
        updatedCityDto.setStatus(newStatus);


        Mockito.when(cityRepository.findById(ID)).thenReturn(Optional.of(cityFromDb));
        Mockito.when(cityRepository.save(cityFromDb)).thenReturn(cityFromDbAfterUpdate);
        Mockito.when(entityToDtoConverterService.convert(cityFromDbAfterUpdate)).thenReturn(updatedCityDto);

        CityDto actual = cityService.update(updatedCityDto);

        assertEquals(expected, actual);
    }

    @Test
    void updateFindByIdException() {
        CityDto city= cityEntityAndCityDtoProvider.getDtoWithId();

        Mockito.when(cityRepository.findById(city.getId())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> cityService.update(city));

        assertEquals(ID, exception.getId());
    }
}