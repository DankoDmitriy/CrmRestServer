package com.danko.crm.service.dto;

import com.danko.crm.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

import static com.danko.crm.service.constant.ValidateErrorMessages.DEPARTMENT_NAME_PROPERTIES;
import static com.danko.crm.service.constant.ValidationRegexp.DEPARTMENT_NAME_REGEXP;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DepartmentDto extends BaseDto {

    @Pattern(regexp = DEPARTMENT_NAME_REGEXP, message = DEPARTMENT_NAME_PROPERTIES)
    private String name;
    private List<PositionDto> positions;

    @Builder
    public DepartmentDto(Long id, LocalDateTime created, LocalDateTime update, Status status,
                         String name, List<PositionDto> positions) {
        super(id, created, update, status);
        this.name = name;
        this.positions = positions;
    }
}
