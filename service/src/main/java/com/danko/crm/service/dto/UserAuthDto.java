package com.danko.crm.service.dto;

import com.danko.crm.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserAuthDto extends BaseDto {
    private String userName;
    private String password;
    private String email;
    private List<RoleDto> roles;

    @Builder
    public UserAuthDto(Long id, LocalDateTime created, LocalDateTime update, Status status,
                       String userName, String password, String email, List<RoleDto> roles) {
        super(id, created, update, status);
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
}
