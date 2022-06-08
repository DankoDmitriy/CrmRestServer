package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.Position;
import com.danko.crm.service.dto.PositionDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PositionToDtoConverter implements Converter<Position, PositionDto> {
    @Override
    public PositionDto convert(Position source) {
        return PositionDto.builder()
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
