package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.Department;
import com.danko.crm.service.dto.DepartmentDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DtoToDepartmentConverter implements Converter<DepartmentDto, Department> {
    @Override
    public Department convert(DepartmentDto source) {
        return Department.builder()
                .id(source.getId())
                .name(source.getName())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .build();
    }
}
