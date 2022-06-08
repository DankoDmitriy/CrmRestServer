package com.danko.crm.service.dto;

import com.danko.crm.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_CONTRACT_NUMBER_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_CONTRACT_OTHER_PROPERTIES;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_CONTRACT_NUMBER_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_CONTRACT_OTHER_REGEXP;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LtdContractDto extends BaseDto {

    @Pattern(regexp = LTD_CONTRACT_NUMBER_REGEXP, message = LTD_CONTRACT_NUMBER_PROPERTIES)
    private String number;

    @Pattern(regexp = LTD_CONTRACT_OTHER_REGEXP, message = LTD_CONTRACT_OTHER_PROPERTIES)
    private String other;
    
    private LocalDateTime contractStart;
    private LtdDto ltd;

    @Builder
    public LtdContractDto(Long id, LocalDateTime created, LocalDateTime update, Status status,
                          LocalDateTime contractStart, String number, String other, LtdDto ltd) {
        super(id, created, update, status);
        this.contractStart = contractStart;
        this.number = number;
        this.other = other;
        this.ltd = ltd;
    }
}
