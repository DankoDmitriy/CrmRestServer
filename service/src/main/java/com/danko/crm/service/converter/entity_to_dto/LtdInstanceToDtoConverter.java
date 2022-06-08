package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.LtdInstance;
import com.danko.crm.service.dto.LtdInstanceDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LtdInstanceToDtoConverter implements Converter<LtdInstance, LtdInstanceDto> {
    @Override
    public LtdInstanceDto convert(LtdInstance source) {
        return LtdInstanceDto.builder()
                .id(source.getId())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .type(source.getType())
                .distanceLocalOffice(source.getDistanceLocalOffice())
                .distanceMainOffice(source.getDistanceMainOffice())
                .others(source.getOthers())
                .telecomCabinet(source.getTelecomCabinet())
                .ups(source.getUps())
                .server(source.getServer())
                .switchs(source.getSwitchs())
                .workplace(source.getWorkplace())
                .equipment(source.getEquipment())
                .address(source.getAddress())
                .build();
    }
}
