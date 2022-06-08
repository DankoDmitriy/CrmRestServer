package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.Employee;
import com.danko.crm.service.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DtoToEmployeeConverter implements Converter<EmployeeDto, Employee> {
    @Override
    public Employee convert(EmployeeDto source) {
        return Employee.builder()
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
