package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.EmployeePhone;
import com.danko.crm.service.dto.EmployeePhoneDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DtoToEmployeePhoneConverter implements Converter<EmployeePhoneDto, EmployeePhone> {
    @Override
    public EmployeePhone convert(EmployeePhoneDto source) {
        return EmployeePhone.builder()
                .id(source.getId())
                .number(source.getNumber())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .build();
    }
}
