package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.Car;
import com.danko.crm.model.City;
import com.danko.crm.service.dto.CarDto;
import com.danko.crm.service.dto.CityDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarToDtoConverter implements Converter<Car, CarDto> {
    @Override
    public CarDto convert(Car source) {
        return CarDto.builder()
                .id(source.getId())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .number(source.getNumber())
                .other(source.getOther())
                .build();
    }
}
