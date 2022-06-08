package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.City;
import com.danko.crm.service.dto.CityDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CityToDtoConverter implements Converter<City, CityDto> {
    @Override
    public CityDto convert(City source) {
        return CityDto.builder()
                .id(source.getId())
                .name(source.getName())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .build();
    }
}
