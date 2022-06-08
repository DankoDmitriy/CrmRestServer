package com.danko.crm.service.dto;

import com.danko.crm.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

import static com.danko.crm.service.constant.ValidateErrorMessages.EMPLOYEE_PHONE_NUMBER_PROPERTIES;
import static com.danko.crm.service.constant.ValidationRegexp.EMPLOYEE_PHONE_NUMBER_REGEXP;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeePhoneDto extends BaseDto {

    @Pattern(regexp = EMPLOYEE_PHONE_NUMBER_REGEXP, message = EMPLOYEE_PHONE_NUMBER_PROPERTIES)
    private String number;

    private PhoneTypeDto phoneType;
    private EmployeeDto employee;

    @Builder
    public EmployeePhoneDto(Long id, LocalDateTime created, LocalDateTime update, Status status, String number,
                            PhoneTypeDto phoneType, EmployeeDto employee) {
        super(id, created, update, status);
        this.number = number;
        this.phoneType = phoneType;
        this.employee = employee;
    }
}
