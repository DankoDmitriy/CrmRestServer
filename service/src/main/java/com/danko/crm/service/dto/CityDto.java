package com.danko.crm.service.dto;

import com.danko.crm.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

import static com.danko.crm.service.constant.ValidateErrorMessages.CITY_NAME_PROPERTIES;
import static com.danko.crm.service.constant.ValidationRegexp.CITY_NAME_REGEXP;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CityDto extends BaseDto {

    @Pattern(regexp = CITY_NAME_REGEXP, message = CITY_NAME_PROPERTIES)
    private String name;

    @Builder
    public CityDto(Long id, LocalDateTime created, LocalDateTime update, Status status, String name) {
        super(id, created, update, status);
        this.name = name;
    }
}
