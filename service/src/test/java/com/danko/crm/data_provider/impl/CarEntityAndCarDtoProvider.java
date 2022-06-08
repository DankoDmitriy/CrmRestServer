package com.danko.crm.data_provider.impl;

import com.danko.crm.data_provider.DataProviderDto;
import com.danko.crm.data_provider.DataProviderEntity;
import com.danko.crm.model.Car;
import com.danko.crm.model.Status;
import com.danko.crm.service.dto.CarDto;

import java.time.LocalDateTime;

public class CarEntityAndCarDtoProvider implements DataProviderDto<CarDto>, DataProviderEntity<Car> {
    private final static Long ID = 1L;
    private final static String NUMBER = "1111 AA-7";
    private final static String OTHER = "Other";
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final CityEntityAndCityDtoProvider cityEntityAndCityDtoProvider = new CityEntityAndCityDtoProvider();

    @Override
    public CarDto getDtoWithIdNotActive() {
        CarDto carDto = getDtoWithId();
        carDto.setStatus(Status.NOT_ACTIVE);
        return carDto;
    }

    @Override
    public Car getEntityWithIdNotActive() {
        Car car = getEntityWithId();
        car.setStatus(Status.NOT_ACTIVE);
        return car;
    }

    @Override
    public CarDto getDtoWithOutId() {
        return CarDto.builder()
                .number(NUMBER)
                .other(OTHER)
                .city(cityEntityAndCityDtoProvider.getDtoWithId())
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Override
    public CarDto getDtoWithId() {
        return CarDto.builder()
                .id(ID)
                .number(NUMBER)
                .other(OTHER)
                .city(cityEntityAndCityDtoProvider.getDtoWithId())
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Override
    public Car getEntityWithOutId() {
        return Car.builder()
                .number(NUMBER)
                .other(OTHER)
                .city(cityEntityAndCityDtoProvider.getEntityWithId())
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Override
    public Car getEntityWithId() {
        return Car.builder()
                .id(ID)
                .number(NUMBER)
                .other(OTHER)
                .city(cityEntityAndCityDtoProvider.getEntityWithId())
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }
}
