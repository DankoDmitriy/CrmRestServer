package com.danko.crm.service.dto;

import com.danko.crm.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleDto extends BaseDto {
    private String name;

    @Builder
    public RoleDto(Long id, LocalDateTime created, LocalDateTime update,
                   Status status, String name) {
        super(id, created, update, status);
        this.name = name;
    }
}
