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

import static com.danko.crm.service.constant.ValidateErrorMessages.POSITION_DESCRIPTION_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.POSITION_NAME_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.POSITION_SUBORDINATION_LEVEL_MAX_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.POSITION_SUBORDINATION_LEVEL_MIN_PROPERTIES;
import static com.danko.crm.service.constant.ValidationRegexp.POSITION_DESCRIPTION_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.POSITION_NAME_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.POSITION_SUBORDINATION_LEVEL_MAX;
import static com.danko.crm.service.constant.ValidationRegexp.POSITION_SUBORDINATION_LEVEL_MIN;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PositionDto extends BaseDto {

    @Pattern(regexp = POSITION_NAME_REGEXP, message = POSITION_NAME_PROPERTIES)
    private String name;

    @Pattern(regexp = POSITION_DESCRIPTION_REGEXP, message = POSITION_DESCRIPTION_PROPERTIES)
    private String description;

    @Min(value = POSITION_SUBORDINATION_LEVEL_MIN, message = POSITION_SUBORDINATION_LEVEL_MIN_PROPERTIES)
    @Max(value = POSITION_SUBORDINATION_LEVEL_MAX, message = POSITION_SUBORDINATION_LEVEL_MAX_PROPERTIES)
    private Integer subordinationLevel;

    private DepartmentDto department;

    @Builder
    public PositionDto(Long id, LocalDateTime created, LocalDateTime update, Status status, String name,
                       String description, Integer subordinationLevel, DepartmentDto department) {
        super(id, created, update, status);
        this.name = name;
        this.description = description;
        this.subordinationLevel = subordinationLevel;
        this.department = department;
    }
}
