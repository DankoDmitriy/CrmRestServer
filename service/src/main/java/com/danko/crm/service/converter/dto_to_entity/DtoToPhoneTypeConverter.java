package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.PhoneType;
import com.danko.crm.service.dto.PhoneTypeDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToPhoneTypeConverter implements Converter<PhoneTypeDto, PhoneType> {
    @Override
    public PhoneType convert(PhoneTypeDto source) {
        return PhoneType.builder()
                .id(source.getId())
                .name(source.getName())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .build();
    }
}
