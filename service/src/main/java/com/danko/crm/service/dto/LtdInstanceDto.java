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

import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_ADDRESS_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_DISTANCE_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_DISTANCE_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_EQUIPMENT_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_EQUIPMENT_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_OTHERS_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_SERVER_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_SERVER_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_SWITCHS_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_SWITCHS_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_TELECOM_CABINET_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_TELECOM_CABINET_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_TYPE_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_TYPE_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_UPS_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_UPS_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_WORKPLACE_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_INSTANCE_WORKPLACE_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_ADDRESS_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_DISTANCE_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_DISTANCE_MIN;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_EQUIPMENT_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_EQUIPMENT_MIN;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_OTHERS_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_SERVER_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_SERVER_MIN;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_SWITCHS_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_SWITCHS_MIN;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_TELECOM_CABINET_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_TELECOM_CABINET_MIN;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_TYPE_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_TYPE_MIN;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_UPS_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_UPS_MIN;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_WORKPLACE_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_INSTANCE_WORKPLACE_MIN;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LtdInstanceDto extends BaseDto {

    @Min(value = LTD_INSTANCE_TYPE_MIN, message = LTD_INSTANCE_TYPE_MIN_PROPERTIES)
    @Max(value = LTD_INSTANCE_TYPE_MAX, message = LTD_INSTANCE_TYPE_MAX_PROPERTIES)
    private Integer type;

    @Min(value = LTD_INSTANCE_DISTANCE_MIN, message = LTD_INSTANCE_DISTANCE_MIN_PROPERTIES)
    @Max(value = LTD_INSTANCE_DISTANCE_MAX, message = LTD_INSTANCE_DISTANCE_MAX_PROPERTIES)
    private Integer distanceMainOffice;

    @Min(value = LTD_INSTANCE_DISTANCE_MIN, message = LTD_INSTANCE_DISTANCE_MIN_PROPERTIES)
    @Max(value = LTD_INSTANCE_DISTANCE_MAX, message = LTD_INSTANCE_DISTANCE_MAX_PROPERTIES)
    private Integer distanceLocalOffice;

    @Pattern(regexp = LTD_INSTANCE_OTHERS_REGEXP, message = LTD_INSTANCE_OTHERS_PROPERTIES)
    private String others;

    @Min(value = LTD_INSTANCE_TELECOM_CABINET_MIN, message = LTD_INSTANCE_TELECOM_CABINET_MIN_PROPERTIES)
    @Max(value = LTD_INSTANCE_TELECOM_CABINET_MAX, message = LTD_INSTANCE_TELECOM_CABINET_MAX_PROPERTIES)
    private Integer telecomCabinet;

    @Min(value = LTD_INSTANCE_UPS_MIN, message = LTD_INSTANCE_UPS_MIN_PROPERTIES)
    @Max(value = LTD_INSTANCE_UPS_MAX, message = LTD_INSTANCE_UPS_MAX_PROPERTIES)
    private Integer ups;

    @Min(value = LTD_INSTANCE_SERVER_MIN, message = LTD_INSTANCE_SERVER_MIN_PROPERTIES)
    @Max(value = LTD_INSTANCE_SERVER_MAX, message = LTD_INSTANCE_SERVER_MAX_PROPERTIES)
    private Integer server;

    @Min(value = LTD_INSTANCE_SWITCHS_MIN, message = LTD_INSTANCE_SWITCHS_MIN_PROPERTIES)
    @Max(value = LTD_INSTANCE_SWITCHS_MAX, message = LTD_INSTANCE_SWITCHS_MAX_PROPERTIES)
    private Integer switchs;

    @Min(value = LTD_INSTANCE_WORKPLACE_MIN, message = LTD_INSTANCE_WORKPLACE_MIN_PROPERTIES)
    @Max(value = LTD_INSTANCE_WORKPLACE_MAX, message = LTD_INSTANCE_WORKPLACE_MAX_PROPERTIES)
    private Integer workplace;

    @Min(value = LTD_INSTANCE_EQUIPMENT_MIN, message = LTD_INSTANCE_EQUIPMENT_MIN_PROPERTIES)
    @Max(value = LTD_INSTANCE_EQUIPMENT_MAX, message = LTD_INSTANCE_EQUIPMENT_MAX_PROPERTIES)
    private Integer equipment;

    @Pattern(regexp = LTD_INSTANCE_ADDRESS_REGEXP, message = LTD_INSTANCE_ADDRESS_PROPERTIES)
    private String address;

    private CityDto city;
    private LtdDto ltd;
    private EmployeeDto employee;

    @Builder
    public LtdInstanceDto(Long id, LocalDateTime created, LocalDateTime update, Status status, Integer type,
                          Integer distanceMainOffice, Integer distanceLocalOffice, String others,
                          Integer telecomCabinet, Integer ups, Integer server, Integer switchs, Integer workplace,
                          Integer equipment, String address, CityDto city, LtdDto ltd, EmployeeDto employee) {
        super(id, created, update, status);
        this.type = type;
        this.distanceMainOffice = distanceMainOffice;
        this.distanceLocalOffice = distanceLocalOffice;
        this.others = others;
        this.telecomCabinet = telecomCabinet;
        this.ups = ups;
        this.server = server;
        this.switchs = switchs;
        this.workplace = workplace;
        this.equipment = equipment;
        this.address = address;
        this.city = city;
        this.ltd = ltd;
        this.employee = employee;
    }
}
