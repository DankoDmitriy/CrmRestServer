package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.TicketType;
import com.danko.crm.service.dto.TicketTypeDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DtoToTicketTypeConverter implements Converter<TicketTypeDto, TicketType> {
    @Override
    public TicketType convert(TicketTypeDto source) {
        return TicketType.builder()
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
