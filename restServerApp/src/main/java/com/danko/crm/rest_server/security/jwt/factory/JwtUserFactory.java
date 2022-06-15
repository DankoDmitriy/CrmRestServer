package com.danko.crm.rest_server.security.jwt.factory;

import com.danko.crm.model.Status;
import com.danko.crm.rest_server.security.jwt.model.JwtUser;
import com.danko.crm.service.dto.RoleDto;
import com.danko.crm.service.dto.UserAuthDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(UserAuthDto userAuthDto) {
        return new JwtUser(
                userAuthDto.getId(),
                userAuthDto.getUserName(),
                userAuthDto.getPassword(),
                userAuthDto.getEmail(),
                userAuthDto.getStatus().equals(Status.ACTIVE),
                userAuthDto.getUpdate(),
                mapToGrantedAuthorities(new ArrayList<>(userAuthDto.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleDto> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
