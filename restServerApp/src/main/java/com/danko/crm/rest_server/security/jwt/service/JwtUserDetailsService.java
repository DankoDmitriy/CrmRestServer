package com.danko.crm.rest_server.security.jwt.service;

import com.danko.crm.rest_server.security.jwt.factory.JwtUserFactory;
import com.danko.crm.rest_server.security.jwt.model.JwtUser;
import com.danko.crm.service.UserAuthService;
import com.danko.crm.service.dto.UserAuthDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private UserAuthService userAuthService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAuthDto> user = userAuthService.findUserByUserName(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user.get());
        return jwtUser;
    }
}
