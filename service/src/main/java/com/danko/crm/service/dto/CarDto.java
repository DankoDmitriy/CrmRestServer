package com.danko.crm.service.dto;

import com.danko.crm.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

import static com.danko.crm.service.constant.ValidateErrorMessages.CAR_NUMBER_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.CAR_OTHER_PROPERTIES;
import static com.danko.crm.service.constant.ValidationRegexp.CAR_NUMBER_OTHER;
import static com.danko.crm.service.constant.ValidationRegexp.CAR_NUMBER_REGEXP;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CarDto extends BaseDto {

    @Pattern(regexp = CAR_NUMBER_REGEXP, message = CAR_NUMBER_PROPERTIES)
    private String number;

    @Pattern(regexp = CAR_NUMBER_OTHER, message = CAR_OTHER_PROPERTIES)
    private String other;
    private CityDto city;

    @Builder
    public CarDto(Long id, LocalDateTime created, LocalDateTime update, Status status,
                  String number, String other, CityDto city) {
        super(id, created, update, status);
        this.number = number;
        this.other = other;
        this.city = city;
    }
}
