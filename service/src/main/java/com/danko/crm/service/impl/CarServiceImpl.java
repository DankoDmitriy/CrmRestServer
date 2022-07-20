package com.danko.crm.service.impl;

import com.danko.crm.model.Car;
import com.danko.crm.model.City;
import com.danko.crm.model.Status;
import com.danko.crm.repository.CarRepository;
import com.danko.crm.service.CarService;
import com.danko.crm.service.CityService;
import com.danko.crm.service.DtoToEntityConverterService;
import com.danko.crm.service.EntityToDtoConverterService;
import com.danko.crm.service.dto.CarDto;
import com.danko.crm.service.dto.CityDto;
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
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private DtoToEntityConverterService dtoToEntityConverterService;
    private EntityToDtoConverterService entityToDtoConverterService;
    private CityService cityService;

    @Override
    public CarDto findById(Long id) {
        return entityToDtoConverterService.convert(findEntityById(id));
    }

    private Car findEntityById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(EXCEPTION_ENTITY_NOT_FOUND_PROPERTIES, id));
    }

    @Override
    @Transactional
    public CarDto save(CarDto carDto) {
        CityDto cityDto = cityService.findById(carDto.getCity().getId());
        if (cityDto.getStatus().equals(Status.ACTIVE)) {
            LocalDateTime now = LocalDateTime.now();

            Car car = dtoToEntityConverterService.convert(carDto);
            car.setCreated(now);
            car.setUpdate(now);
            car.setStatus(Status.ACTIVE);
            car.setCity(dtoToEntityConverterService.convert(cityDto));

            return entityToDtoConverterService.convert(carRepository.save(car));
        } else {
            throw new NestedEntityInactiveException(EXCEPTION_NESTED_ENTITY_INACTIVE_PROPERTIES);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Car car = findEntityById(id);
        car.setStatus(Status.DELETED);
        carRepository.save(car);
    }

    @Override
    public Page<CarDto> findAll(Pageable pageable) {
        return carRepository.findAll(pageable).map(entityToDtoConverterService::convert);
    }

    @Override
    @Transactional
    public CarDto update(CarDto carDto) {
        Car carFromDb = findEntityById(carDto.getId());
        checkCarBeforeUpdate(carFromDb, carDto);
        return entityToDtoConverterService.convert(carRepository.save(carFromDb));
    }

    @Override
    public Page<CarDto> findAllByStatus(Pageable pageable, Status status) {
        return carRepository.findAllByStatus(pageable, status).map(entityToDtoConverterService::convert);
    }

    private void checkCarBeforeUpdate(Car carFromDb, CarDto carDto) {
        LocalDateTime now = LocalDateTime.now();

        if (carDto.getNumber() != null && !carDto.getNumber().equals(carFromDb.getNumber())) {
            carFromDb.setNumber(carDto.getNumber());
        }

        if (carDto.getOther() != null && !carDto.getOther().equals(carFromDb.getOther())) {
            carFromDb.setOther(carDto.getOther());
        }

        if (carDto.getStatus() != null && !carDto.getStatus().equals(carFromDb.getStatus())) {
            carFromDb.setStatus(carDto.getStatus());
        }

        if (carDto.getCity() != null && !carDto.getCity().getId().equals(carFromDb.getCity().getId())) {
            City city = dtoToEntityConverterService.convert(
                    cityService.findById(carDto.getCity().getId()));
            carFromDb.setCity(city);
        }
        carFromDb.setUpdate(now);
    }
}
