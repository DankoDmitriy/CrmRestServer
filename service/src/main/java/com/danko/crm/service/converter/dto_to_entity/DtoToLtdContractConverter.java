package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.LtdContract;
import com.danko.crm.service.dto.LtdContractDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DtoToLtdContractConverter implements Converter<LtdContractDto, LtdContract> {
    @Override
    public LtdContract convert(LtdContractDto source) {
        return LtdContract.builder()
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
