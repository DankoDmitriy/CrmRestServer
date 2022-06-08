package com.danko.crm.service.converter.entity_to_dto;

import com.danko.crm.model.Ticket;
import com.danko.crm.service.dto.TicketDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TicketToDtoConverter implements Converter<Ticket, TicketDto> {
    @Override
    public TicketDto convert(Ticket source) {
        return TicketDto.builder()
                .id(source.getId())
                .openStatus(source.getOpenStatus())
                .created(source.getCreated())
                .update(source.getUpdate())
                .status(source.getStatus())
                .dateOfReceiving(source.getDateOfReceiving())
                .dateOfFinish(source.getDateOfFinish())
                .dateCustomersDepartmentDoc(source.getDateCustomersDepartmentDoc())
                .dateAccountingDepartmentDoc(source.getDateAccountingDepartmentDoc())
                .dateTransferDoc(source.getDateTransferDoc())
                .systemTicketId(source.getSystemTicketId())
                .waybill(source.getWaybill())
                .server(source.getServer())
                .ups(source.getServer())
                .switchs(source.getSwitchs())
                .workplace(source.getSwitchs())
                .equipment(source.getEquipment())
                .employeeExecutorDov(source.getEmployeeExecutorDov())
                .job(source.getJob())
                .other(source.getOther())
                .build();
    }
}
