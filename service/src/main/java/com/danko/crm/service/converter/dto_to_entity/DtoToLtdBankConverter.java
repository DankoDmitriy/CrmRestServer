package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.LtdBank;
import com.danko.crm.service.dto.LtdBankDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DtoToLtdBankConverter implements Converter<LtdBankDto, LtdBank> {
    @Override
    public LtdBank convert(LtdBankDto source) {
        return LtdBank.builder()
                .id(source.getId())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .requisites(source.getRequisites())
                .build();
    }
}
