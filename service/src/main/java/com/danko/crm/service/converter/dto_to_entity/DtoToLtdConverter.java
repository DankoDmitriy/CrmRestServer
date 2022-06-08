package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.Ltd;
import com.danko.crm.service.dto.LtdDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DtoToLtdConverter implements Converter<LtdDto, Ltd> {
    @Override
    public Ltd convert(LtdDto source) {
        return Ltd.builder()
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
