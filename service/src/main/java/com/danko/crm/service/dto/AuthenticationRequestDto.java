package com.danko.crm.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

import static com.danko.crm.service.constant.ValidateErrorMessages.AUTHENTICATION_REQUEST_PASSWORD_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.AUTHENTICATION_REQUEST_USER_NAME_PROPERTIES;
import static com.danko.crm.service.constant.ValidationRegexp.AUTHENTICATION_REQUEST_PASSWORD;
import static com.danko.crm.service.constant.ValidationRegexp.AUTHENTICATION_REQUEST_USER_NAME;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDto {

    @Pattern(regexp = AUTHENTICATION_REQUEST_USER_NAME, message = AUTHENTICATION_REQUEST_USER_NAME_PROPERTIES)
    private String username;

    @Pattern(regexp = AUTHENTICATION_REQUEST_PASSWORD, message = AUTHENTICATION_REQUEST_PASSWORD_PROPERTIES)
    private String password;
}
