package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.LtdBank;
import com.danko.crm.service.dto.LtdBankDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LtdBankToDtoConverter implements Converter<LtdBank, LtdBankDto> {
    @Override
    public LtdBankDto convert(LtdBank source) {
        return LtdBankDto.builder()
                .id(source.getId())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .requisites(source.getRequisites())
                .build();
    }
}
