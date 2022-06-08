package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.Ltd;
import com.danko.crm.service.dto.LtdDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LtdToDtoConverter implements Converter<Ltd, LtdDto> {
    @Override
    public LtdDto convert(Ltd source) {
        return LtdDto.builder()
                .id(source.getId())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .nameFull(source.getNameFull())
                .nameShort(source.getNameShort())
                .address(source.getAddress())
                .unp(source.getUnp())
                .build();
    }
}
