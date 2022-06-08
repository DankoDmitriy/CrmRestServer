package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.LtdContract;
import com.danko.crm.service.dto.LtdContractDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LtdContractToDtoConverter implements Converter<LtdContract, LtdContractDto> {
    @Override
    public LtdContractDto convert(LtdContract source) {
        return LtdContractDto.builder()
                .id(source.getId())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .contractStart(source.getContractStart())
                .number(source.getNumber())
                .other(source.getOther())
                .build();
    }
}
