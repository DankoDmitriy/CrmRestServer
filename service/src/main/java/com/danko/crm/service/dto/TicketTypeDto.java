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

import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_TYPE_ACTION_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_TYPE_ACTION_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_TYPE_NAME_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_TYPE_PRIORITY_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.TICKET_TYPE_PRIORITY_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_TYPE_ACTION_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_TYPE_ACTION_MIN;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_TYPE_NAME_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_TYPE_PRIORITY_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.TICKET_TYPE_PRIORITY_MIN;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TicketTypeDto extends BaseDto {

    @Pattern(regexp = TICKET_TYPE_NAME_REGEXP, message = TICKET_TYPE_NAME_PROPERTIES)
    private String name;

    @Min(value = TICKET_TYPE_ACTION_MIN, message = TICKET_TYPE_ACTION_MIN_PROPERTIES)
    @Max(value = TICKET_TYPE_ACTION_MAX, message = TICKET_TYPE_ACTION_MAX_PROPERTIES)
    private Integer action;

    @Min(value = TICKET_TYPE_PRIORITY_MIN, message = TICKET_TYPE_PRIORITY_MIN_PROPERTIES)
    @Max(value = TICKET_TYPE_PRIORITY_MAX, message = TICKET_TYPE_PRIORITY_MAX_PROPERTIES)
    private Integer priority;

    @Builder
    public TicketTypeDto(Long id, LocalDateTime created, LocalDateTime update, Status status, String name,
                         Integer action, Integer priority) {
        super(id, created, update, status);
        this.name = name;
        this.action = action;
        this.priority = priority;
    }
}
