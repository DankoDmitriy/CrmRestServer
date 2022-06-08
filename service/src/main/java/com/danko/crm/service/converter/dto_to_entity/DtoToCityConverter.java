package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.City;
import com.danko.crm.service.dto.CityDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToCityConverter implements Converter<CityDto, City> {
    @Override
    public City convert(CityDto source) {
        return City.builder()
                .id(source.getId())
                .name(source.getName())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .build();
    }
}
