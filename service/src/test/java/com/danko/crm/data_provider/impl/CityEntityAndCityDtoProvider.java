package com.danko.crm.data_provider.impl;

import com.danko.crm.data_provider.DataProviderDto;
import com.danko.crm.data_provider.DataProviderEntity;
import com.danko.crm.model.City;
import com.danko.crm.model.Status;
import com.danko.crm.service.dto.CityDto;

import java.time.LocalDateTime;

public class CityEntityAndCityDtoProvider implements DataProviderDto<CityDto>, DataProviderEntity<City> {
    private final static Long ID = 1L;
    private final static String NAME = "Minsk";
    private final LocalDateTime localDateTime = LocalDateTime.now();

    @Override
    public CityDto getDtoWithOutId() {
        return CityDto.builder()
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Override
    public CityDto getDtoWithId() {
        return CityDto.builder()
                .id(ID)
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Override
    public CityDto getDtoWithIdNotActive() {
        CityDto cityDto = getDtoWithId();
        cityDto.setStatus(Status.NOT_ACTIVE);
        return cityDto;
    }

    @Override
    public City getEntityWithOutId() {
        return City.builder()
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Override
    public City getEntityWithId() {
        return City.builder()
                .id(ID)
                .name(NAME)
                .created(localDateTime)
                .update(localDateTime)
                .status(Status.ACTIVE)
                .build();
    }

    @Override
    public City getEntityWithIdNotActive() {
        City city = getEntityWithId();
        city.setStatus(Status.NOT_ACTIVE);
        return city;
    }
}
