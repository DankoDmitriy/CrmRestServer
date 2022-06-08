package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.EmployeePhone;
import com.danko.crm.service.dto.EmployeePhoneDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeePhoneToDtoConverter implements Converter<EmployeePhone, EmployeePhoneDto> {
    @Override
    public EmployeePhoneDto convert(EmployeePhone source) {
        return EmployeePhoneDto.builder()
                .id(source.getId())
                .number(source.getNumber())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .build();
    }
}
