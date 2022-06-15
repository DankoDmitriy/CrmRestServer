package com.danko.crm.rest_server.controller;

import com.danko.crm.rest_server.security.jwt.provider.JwtTokenProvider;
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

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin()
@RequestMapping(value = "/server/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserAuthService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login( @Valid @RequestBody AuthenticationRequestDto requestDto) {
        try {

            String username = requestDto.getUsername();
            System.out.println(requestDto.getUsername());
            System.out.println(requestDto.getPassword());
            System.out.println(passwordEncoder.encode(requestDto.getPassword()));

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            Optional<UserAuthDto> userAuthDtoOptional = userService.findUserByUserName(username);

            if (!userAuthDtoOptional.isPresent()) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, userAuthDtoOptional.get().getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
