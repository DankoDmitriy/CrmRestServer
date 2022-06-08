package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.Car;
import com.danko.crm.service.dto.CarDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DtoToCarConverter implements Converter<CarDto, Car> {
    @Override
    public Car convert(CarDto source) {
        return Car.builder()
                .id(source.getId())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .number(source.getNumber())
                .other(source.getOther())
                .build();
    }
}
