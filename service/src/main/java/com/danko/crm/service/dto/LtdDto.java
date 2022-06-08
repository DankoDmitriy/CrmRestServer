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

import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_ADDRESS_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_NAME_FULL_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_NAME_SHORT_PROPERTIES;
import static com.danko.crm.service.constant.ValidateErrorMessages.LTD_UNP_PROPERTIES;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_ADDRESS_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_NAME_FULL_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_NAME_SHORT_REGEXP;
import static com.danko.crm.service.constant.ValidationRegexp.LTD_UNP_REGEXP;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LtdDto extends BaseDto {

    @Pattern(regexp = LTD_NAME_FULL_REGEXP, message = LTD_NAME_FULL_PROPERTIES)
    private String nameFull;

    @Pattern(regexp = LTD_NAME_SHORT_REGEXP, message = LTD_NAME_SHORT_PROPERTIES)
    private String nameShort;

    @Pattern(regexp = LTD_ADDRESS_REGEXP, message = LTD_ADDRESS_PROPERTIES)
    private String address;

    @Pattern(regexp = LTD_UNP_REGEXP, message = LTD_UNP_PROPERTIES)
    private String unp;
    private List<LtdBankDto> ltdBanks;
    private List<LtdContractDto> ltdContracts;
    private List<LtdInstanceDto> ltdInstances;

    @Builder
    public LtdDto(Long id, LocalDateTime created, LocalDateTime update, Status status,
                  String nameFull, String nameShort, String address, String unp, List<LtdBankDto> ltdBanks,
                  List<LtdContractDto> ltdContracts, List<LtdInstanceDto> ltdInstances) {
        super(id, created, update, status);
        this.nameFull = nameFull;
        this.nameShort = nameShort;
        this.address = address;
        this.unp = unp;
        this.ltdBanks = ltdBanks;
        this.ltdContracts = ltdContracts;
        this.ltdInstances = ltdInstances;
    }
}
