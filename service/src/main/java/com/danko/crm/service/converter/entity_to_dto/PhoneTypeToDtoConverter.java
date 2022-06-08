package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.PhoneType;
import com.danko.crm.service.dto.PhoneTypeDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PhoneTypeToDtoConverter implements Converter<PhoneType, PhoneTypeDto> {
    @Override
    public PhoneTypeDto convert(PhoneType source) {
        return PhoneTypeDto.builder()
                .id(source.getId())
                .name(source.getName())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .build();
    }
}
