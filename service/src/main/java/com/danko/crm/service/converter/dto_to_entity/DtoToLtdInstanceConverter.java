package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.LtdInstance;
import com.danko.crm.service.dto.LtdInstanceDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DtoToLtdInstanceConverter implements Converter<LtdInstanceDto, LtdInstance> {
    @Override
    public LtdInstance convert(LtdInstanceDto source) {
        return LtdInstance.builder()
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
