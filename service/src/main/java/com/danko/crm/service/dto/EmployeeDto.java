package com.danko.crm.service.dto;

import com.danko.crm.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

import static com.danko.crm.service.constant.ValidateErrorMessages.EMPLOYEE_COMMENT_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.EMPLOYEE_EMAIL_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.EMPLOYEE_FIRST_NAME_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.EMPLOYEE_LAST_NAME_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.EMPLOYEE_PATRONYMIC_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.EMPLOYEE_PROXY_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.EMPLOYEE_TG_ID_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.EMPLOYEE_USER_NAME_PROPERTIES;
import static com.danko.crm.service.constant.ValidationRegexp.EMPLOYEE_COMMENT_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.EMPLOYEE_FIRST_NAME_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.EMPLOYEE_LAST_NAME_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.EMPLOYEE_PATRONYMIC_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.EMPLOYEE_PROXY_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.EMPLOYEE_TG_ID_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.EMPLOYEE_USER_NAME_REGEXP;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeDto extends BaseDto {

    @Pattern(regexp = EMPLOYEE_FIRST_NAME_REGEXP, message = EMPLOYEE_FIRST_NAME_PROPERTIES)
    private String firstName;

    @Pattern(regexp = EMPLOYEE_LAST_NAME_REGEXP, message = EMPLOYEE_LAST_NAME_PROPERTIES)
    private String lastName;

    @Pattern(regexp = EMPLOYEE_PATRONYMIC_REGEXP, message = EMPLOYEE_PATRONYMIC_PROPERTIES)
    private String patronymic;

    private LocalDateTime birthday;
    private LocalDateTime contractStart;
    private LocalDateTime contractFinish;

    @Pattern(regexp = EMPLOYEE_USER_NAME_REGEXP, message = EMPLOYEE_USER_NAME_PROPERTIES)
    private String userName;

    @Email(message = EMPLOYEE_EMAIL_PROPERTIES)
    private String email;

    @Pattern(regexp = EMPLOYEE_PROXY_REGEXP, message = EMPLOYEE_PROXY_PROPERTIES)
    private String dov;

    @Pattern(regexp = EMPLOYEE_TG_ID_REGEXP, message = EMPLOYEE_TG_ID_PROPERTIES)
    private Integer tgId;

    @Pattern(regexp = EMPLOYEE_COMMENT_REGEXP, message = EMPLOYEE_COMMENT_PROPERTIES)
    private String comment;

    private CityDto city;
    private PositionDto position;
    private DepartmentDto department;
    private List<EmployeePhoneDto> phones;
    private List<RoleDto> roles;

    @Builder
    public EmployeeDto(Long id, LocalDateTime created, LocalDateTime update, Status status, String firstName,
                       String lastName, String patronymic, LocalDateTime birthday, LocalDateTime contractStart,
                       LocalDateTime contractFinish, String userName, String email, String dov, Integer tgId,
                       String comment, CityDto city, PositionDto position, DepartmentDto department,
                       List<EmployeePhoneDto> phones, List<RoleDto> roles) {
        super(id, created, update, status);
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.contractStart = contractStart;
        this.contractFinish = contractFinish;
        this.userName = userName;
        this.email = email;
        this.dov = dov;
        this.tgId = tgId;
        this.comment = comment;
        this.city = city;
        this.position = position;
        this.department = department;
        this.phones = phones;
        this.roles = roles;
    }
}
