package com.danko.crm.rest_server.controller;

import com.danko.crm.rest_server.security.jwt.provider.JwtTokenProvider;
import com.danko.crm.rest_server.security.jwt.service.JwtRefreshService;
import com.danko.crm.service.UserAuthService;
import com.danko.crm.service.dto.AuthenticationRequestDto;
import com.danko.crm.service.dto.UserAuthDto;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin()
@RequestMapping(value = "/server/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class LoginController {
    private static final String ACCESS_TOKEN = "token";
    private static final String REFRESH_TOKEN = "refreshToken";
    private static final String USER_NAME = "username";
    private static final String USER_WITH_USER_NAME_NOT_FOUND = "User not found";
    private static final String INVALID_ACCESS_DATA = "Invalid username or password";

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtRefreshService jwtRefreshService;
    private final UserAuthService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthenticationRequestDto requestDto) {
        try {

            String username = requestDto.getUsername();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            Optional<UserAuthDto> userAuthDtoOptional = userService.findUserByUserName(username);

            if (!userAuthDtoOptional.isPresent()) {
                throw new UsernameNotFoundException(USER_WITH_USER_NAME_NOT_FOUND);
            }

            String token = jwtTokenProvider.createToken(
                    username,
                    userAuthDtoOptional.get().getRoles(),
                    userAuthDtoOptional.get().getId());

            String refreshToken = jwtTokenProvider.createRefreshToken(username, userAuthDtoOptional.get().getId());

            Map<Object, Object> response = new HashMap<>();
            response.put(USER_NAME, username);
            response.put(ACCESS_TOKEN, token);
            response.put(REFRESH_TOKEN, refreshToken);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(INVALID_ACCESS_DATA);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity getNewToken(String refreshToken, ServletRequest req) {
        Map<Object, Object> response = new HashMap<>();
        jwtRefreshService.generateNewAccessAndRefreshToken(response, req, refreshToken);
        return ResponseEntity.ok(response);
    }
}
