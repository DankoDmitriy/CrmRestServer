package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.Employee;
import com.danko.crm.service.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeToDtoConverter implements Converter<Employee, EmployeeDto> {
    @Override
    public EmployeeDto convert(Employee source) {
        return EmployeeDto.builder()
                .id(source.getId())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .patronymic(source.getPatronymic())
                .birthday(source.getBirthday())
                .contractStart(source.getContractStart())
                .contractFinish(source.getContractFinish())
                .userName(source.getUserName())
                .email(source.getEmail())
                .tgId(source.getTgId())
                .comment(source.getComment())
                .dov(source.getDov())
                .build();
    }
}
