package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.Position;
import com.danko.crm.service.dto.PositionDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DtoToPositionConverter implements Converter<PositionDto, Position> {
    @Override
    public Position convert(PositionDto source) {
        return Position.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .subordinationLevel(source.getSubordinationLevel())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .build();
    }
}
