package com.danko.crm.service.response_entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private String errorMessage;
    private List<String> incorrectParameters;
    private String errorTime;
    private String errorCode;
}
