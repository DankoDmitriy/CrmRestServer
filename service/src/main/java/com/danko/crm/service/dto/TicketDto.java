package com.danko.crm.service.dto;

import com.danko.crm.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_EQUIPMENT_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_EQUIPMENT_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_JOB_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_OPEN_STATUS_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_OPEN_STATUS_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_OTHER_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_SERVER_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_SERVER_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_SWITCHS_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_SWITCHS_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_SYSTEM_TICKET_ID_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_SYSTEM_WAYBILL_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_UPS_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_UPS_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_WORKPLACE_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_WORKPLACE_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_EQUIPMENT_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_EQUIPMENT_MIN;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_JOB_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_OPEN_STATUS_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_OPEN_STATUS_MIN;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_OTHER_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_SERVER_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_SERVER_MIN;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_SWITCHS_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_SWITCHS_MIN;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_SYSTEM_TICKET_ID_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_SYSTEM_WAYBILL_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_UPS_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_UPS_MIN;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_WORKPLACE_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_WORKPLACE_MIN;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TicketDto extends BaseDto {

    @Min(value = TICKET_OPEN_STATUS_MIN, message = TICKET_OPEN_STATUS_MIN_PROPERTIES)
    @Max(value = TICKET_OPEN_STATUS_MAX, message = TICKET_OPEN_STATUS_MAX_PROPERTIES)
    private Integer openStatus;

    private LocalDateTime dateOfReceiving;
    private LocalDateTime dateOfFinish;
    private LocalDateTime dateCustomersDepartmentDoc;
    private LocalDateTime dateAccountingDepartmentDoc;
    private LocalDateTime dateTransferDoc;

    @Pattern(regexp = TICKET_SYSTEM_TICKET_ID_REGEXP, message = TICKET_SYSTEM_TICKET_ID_PROPERTIES)
    private String systemTicketId;

    @Pattern(regexp = TICKET_SYSTEM_WAYBILL_REGEXP, message = TICKET_SYSTEM_WAYBILL_PROPERTIES)
    private String waybill;

    @Min(value = TICKET_SERVER_MIN, message = TICKET_SERVER_MIN_PROPERTIES)
    @Max(value = TICKET_SERVER_MAX, message = TICKET_SERVER_MAX_PROPERTIES)
    private Integer server;

    @Min(value = TICKET_UPS_MIN, message = TICKET_UPS_MIN_PROPERTIES)
    @Max(value = TICKET_UPS_MAX, message = TICKET_UPS_MAX_PROPERTIES)
    private Integer ups;

    @Min(value = TICKET_SWITCHS_MIN, message = TICKET_SWITCHS_MIN_PROPERTIES)
    @Max(value = TICKET_SWITCHS_MAX, message = TICKET_SWITCHS_MAX_PROPERTIES)
    private Integer switchs;

    @Min(value = TICKET_WORKPLACE_MIN, message = TICKET_WORKPLACE_MIN_PROPERTIES)
    @Max(value = TICKET_WORKPLACE_MAX, message = TICKET_WORKPLACE_MAX_PROPERTIES)
    private Integer workplace;

    @Min(value = TICKET_EQUIPMENT_MIN, message = TICKET_EQUIPMENT_MIN_PROPERTIES)
    @Max(value = TICKET_EQUIPMENT_MAX, message = TICKET_EQUIPMENT_MAX_PROPERTIES)
    private Integer equipment;

    private String employeeExecutorDov;

    @Pattern(regexp = TICKET_JOB_REGEXP, message = TICKET_JOB_PROPERTIES)
    private String job;

    @Pattern(regexp = TICKET_OTHER_REGEXP, message = TICKET_OTHER_PROPERTIES)
    private String other;
    private CarDto car;
    private LtdInstanceDto ltdInstance;
    private EmployeeDto employeeExecutor;
    private EmployeeDto employeeOwner;
    private EmployeeDto employeeRecipient;
    private TicketTypeDto ticketType;

    @Builder
    public TicketDto(Long id, Integer openStatus, LocalDateTime created, LocalDateTime update, Status status,
                     LocalDateTime dateOfReceiving, LocalDateTime dateOfFinish,
                     LocalDateTime dateCustomersDepartmentDoc, LocalDateTime dateAccountingDepartmentDoc,
                     LocalDateTime dateTransferDoc, String systemTicketId, String waybill, Integer server,
                     Integer ups, Integer switchs, Integer workplace, Integer equipment, String employeeExecutorDov,
                     String job, String other, CarDto car, LtdInstanceDto ltdInstance, EmployeeDto employeeExecutor,
                     EmployeeDto employeeOwner, EmployeeDto employeeRecipient, TicketTypeDto ticketType) {
        super(id, created, update, status);
        this.openStatus = openStatus;
        this.dateOfReceiving = dateOfReceiving;
        this.dateOfFinish = dateOfFinish;
        this.dateCustomersDepartmentDoc = dateCustomersDepartmentDoc;
        this.dateAccountingDepartmentDoc = dateAccountingDepartmentDoc;
        this.dateTransferDoc = dateTransferDoc;
        this.systemTicketId = systemTicketId;
        this.waybill = waybill;
        this.server = server;
        this.ups = ups;
        this.switchs = switchs;
        this.workplace = workplace;
        this.equipment = equipment;
        this.employeeExecutorDov = employeeExecutorDov;
        this.job = job;
        this.other = other;
        this.car = car;
        this.ltdInstance = ltdInstance;
        this.employeeExecutor = employeeExecutor;
        this.employeeOwner = employeeOwner;
        this.employeeRecipient = employeeRecipient;
        this.ticketType = ticketType;
    }
}
