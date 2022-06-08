package com.danko.crm.service.impl;

import com.danko.crm.data_provider.impl.CarEntityAndCarDtoProvider;
import com.danko.crm.data_provider.impl.CityEntityAndCityDtoProvider;
import com.danko.crm.model.Car;
import com.danko.crm.model.Status;
import com.danko.crm.repository.CarRepository;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.dto.CarDto;
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
class CarServiceImplTest {
    private final static Long ID = 1L;

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CityServiceImpl cityService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private DtoToEntityConverterService dtoToEntityConverterService;

    @Mock
    private EntityToDtoConverterService entityToDtoConverterService;

    private final CarEntityAndCarDtoProvider carEntityAndCarDtoProvider = new CarEntityAndCarDtoProvider();
    private final CityEntityAndCityDtoProvider cityEntityAndCityDtoProvider = new CityEntityAndCityDtoProvider();

    @Test
    void findById() {
        CarDto expected = carEntityAndCarDtoProvider.getDtoWithId();
        Car carFromDb = carEntityAndCarDtoProvider.getEntityWithId();

        Mockito.when(carRepository.findById(expected.getId())).thenReturn(Optional.of(carFromDb));

        Mockito.when(entityToDtoConverterService.convert(carFromDb)).thenReturn(expected);

        CarDto actual = carService.findById(expected.getId());

        assertEquals(expected, actual);
    }

    @Test
    void findByIdException() {
        Mockito.when(carRepository.findById(ID)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> carService.findById(ID));
        assertEquals(ID, exception.getId());
    }

    @Test
    void save() {
        CarDto expected = carEntityAndCarDtoProvider.getDtoWithId();
        Car carFromDb = carEntityAndCarDtoProvider.getEntityWithId();

        Mockito.when(entityToDtoConverterService.convert(carFromDb)).thenReturn(expected);
        Mockito.when(dtoToEntityConverterService.convert(expected)).thenReturn(carFromDb);
        Mockito.when(carRepository.save(carFromDb)).thenReturn(carFromDb);
        Mockito.when(cityService.findById(cityEntityAndCityDtoProvider.getDtoWithId().getId()))
                .thenReturn(cityEntityAndCityDtoProvider.getDtoWithId());

        CarDto actual = carService.save(expected);

        assertEquals(expected, actual);
    }

    @Test()
    void saveException() {
        CarDto expected = carEntityAndCarDtoProvider.getDtoWithId();

        Mockito.when(cityService.findById(cityEntityAndCityDtoProvider.getDtoWithIdNotActive().getId()))
                .thenReturn(cityEntityAndCityDtoProvider.getDtoWithIdNotActive());


        NestedEntityInactiveException exception = assertThrows(NestedEntityInactiveException.class, () -> {
            carService.save(expected);
        });
        assertNotNull(exception.getErrorMessage());
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<Car> cars = new PageImpl<>(Arrays.asList(carEntityAndCarDtoProvider.getEntityWithId()));
        Page<CarDto> expected = new PageImpl<>(Arrays.asList(carEntityAndCarDtoProvider.getDtoWithId()));

        Mockito.when(carRepository.findAll(pageable)).thenReturn(cars);
        Mockito.when(entityToDtoConverterService.convert(carEntityAndCarDtoProvider.getEntityWithId()))
                .thenReturn(carEntityAndCarDtoProvider.getDtoWithId());

        Page<CarDto> actual = carService.findAll(pageable);

        assertEquals(expected, actual);
    }

    @Test
    void findAllByStatus() {
        Status status = Status.NOT_ACTIVE;
        Pageable pageable = PageRequest.of(0, 10);

        Page<Car> cars = new PageImpl<>(Arrays.asList(carEntityAndCarDtoProvider.getEntityWithIdNotActive()));
        Page<CarDto> expected = new PageImpl<>(Arrays.asList(carEntityAndCarDtoProvider.getDtoWithIdNotActive()));

        Mockito.when(carRepository.findAllByStatus(pageable, status)).thenReturn(cars);
        Mockito.when(entityToDtoConverterService.convert(carEntityAndCarDtoProvider.getEntityWithIdNotActive()))
                .thenReturn(carEntityAndCarDtoProvider.getDtoWithIdNotActive());

        Page<CarDto> actual = carService.findAllByStatus(pageable, status);

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        String newCarNumber = "1234 CC-7";
        String newOther = "new Other";
        Status newStatus = Status.NOT_ACTIVE;

        Car carFromDb = carEntityAndCarDtoProvider.getEntityWithId();

        Car carFromDbAfterUpdate = carEntityAndCarDtoProvider.getEntityWithId();
        carFromDbAfterUpdate.setNumber(newCarNumber);
        carFromDbAfterUpdate.setOther(newOther);
        carFromDbAfterUpdate.setStatus(newStatus);

        CarDto expected = carEntityAndCarDtoProvider.getDtoWithId();
        expected.setNumber(newCarNumber);
        expected.setOther(newOther);
        expected.setStatus(newStatus);

        CarDto updatedCarDto = carEntityAndCarDtoProvider.getDtoWithId();
        updatedCarDto.setNumber(newCarNumber);
        updatedCarDto.setOther(newOther);
        updatedCarDto.setStatus(newStatus);

        Mockito.when(carRepository.findById(ID)).thenReturn(Optional.of(carFromDb));
        Mockito.when(carRepository.save(carFromDb)).thenReturn(carFromDbAfterUpdate);
        Mockito.when(entityToDtoConverterService.convert(carFromDbAfterUpdate)).thenReturn(updatedCarDto);

        CarDto actual = carService.update(updatedCarDto);

        assertEquals(expected, actual);
    }

    @Test
    void updateFindByIdException() {
        CarDto carDto = carEntityAndCarDtoProvider.getDtoWithId();

        Mockito.when(carRepository.findById(carDto.getId())).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> carService.update(carDto));

        assertEquals(ID, exception.getId());
    }
}
