package com.danko.crm.service.dto;

import com.danko.crm.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_BANK_REQUISITES_PROPERTIES;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_BANK_REQUISITES_REGEXP;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LtdBankDto extends BaseDto {

    @Pattern(regexp = LTD_BANK_REQUISITES_REGEXP, message = LTD_BANK_REQUISITES_PROPERTIES)
    private String requisites;
    private LtdDto ltd;

    @Builder
    public LtdBankDto(Long id, LocalDateTime created, LocalDateTime update, Status status,
                      String requisites, LtdDto ltd) {
        super(id, created, update, status);
        this.requisites = requisites;
        this.ltd = ltd;
    }
}
