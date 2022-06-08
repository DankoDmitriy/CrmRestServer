package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.TicketType;
import com.danko.crm.service.dto.TicketTypeDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TicketTypeToDtoConverter implements Converter<TicketType, TicketTypeDto> {
    @Override
    public TicketTypeDto convert(TicketType source) {
        return TicketTypeDto.builder()
                .id(source.getId())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .name(source.getName())
                .action(source.getAction())
                .priority(source.getPriority())
                .build();
    }
}
