package com.danko.crm.service.converter.dto_to_entity;

import com.danko.crm.model.Ticket;
import com.danko.crm.service.dto.TicketDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DtoToTicketConverter implements Converter<TicketDto, Ticket> {
    @Override
    public Ticket convert(TicketDto source) {
        return Ticket.builder()
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
