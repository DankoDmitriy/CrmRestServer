package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.Department;
import com.danko.crm.service.dto.DepartmentDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DepartmentToDtoConverter implements Converter<Department, DepartmentDto> {
    @Override
    public DepartmentDto convert(Department source) {
        return DepartmentDto.builder()
                .id(source.getId())
                .name(source.getName())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .build();
    }
}
