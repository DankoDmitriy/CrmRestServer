package com.danko.crm.rest_server.security.jwt.service;

import com.danko.crm.model.RefreshToken;
import com.danko.crm.model.Status;
import com.danko.crm.rest_server.security.jwt.exception.RefreshTokenNotFoundException;
import com.danko.crm.rest_server.security.jwt.provider.JwtTokenProvider;
import com.danko.crm.service.JwtRefreshTokenService;
import com.danko.crm.service.UserAuthService;
import com.danko.crm.service.dto.UserAuthDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@Service
public class JwtRefreshService {
    private static final String ACCESS_TOKEN = "token";
    private static final String REFRESH_TOKEN = "refreshToken";
    private static final String USER_NAME = "username";
    private static final String FIELD_NAME_USER_NAME_IN_ACCESS_TOKEN = "sub";
    private static final String USER_WITH_USER_NAME_NOT_FOUND = "User not found";
    private static final String REFRESH_TOKEN_NOT_FOUND_EXCEPTION_MESSAGE = "refresh.token.not.found.exception.message";

    @Value("${jwt.token.secret}")
    private String secret;

    @Autowired
    private JwtRefreshTokenService jwtRefreshTokenService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserAuthService userAuthService;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public boolean checkRefreshToken(String refreshToken) {
        return jwtTokenProvider.validateToken(refreshToken) && checkRefreshTokenInDatabase(refreshToken);
    }

    private boolean checkRefreshTokenInDatabase(String refreshToken) {
        return jwtRefreshTokenService.findByTokenAndStatus(refreshToken, Status.ACTIVE).isPresent();
    }

    public void generateNewAccessAndRefreshToken(Map<Object, Object> response, ServletRequest req, String refreshToken) {
        Optional<RefreshToken> optionalRefreshToken = jwtRefreshTokenService.findByTokenAndStatus(refreshToken, Status.ACTIVE);

        optionalRefreshToken.orElseThrow(
                () -> new RefreshTokenNotFoundException(REFRESH_TOKEN_NOT_FOUND_EXCEPTION_MESSAGE));

        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        if (token != null) {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            String username = (String) claims.getBody().get(FIELD_NAME_USER_NAME_IN_ACCESS_TOKEN);

            Optional<UserAuthDto> userAuthDtoOptional = userAuthService.findUserByUserName(username);

            if (!userAuthDtoOptional.isPresent()) {
                throw new UsernameNotFoundException(USER_WITH_USER_NAME_NOT_FOUND);
            }

            String accessTokenNew = jwtTokenProvider.createToken(
                    username,
                    userAuthDtoOptional.get().getRoles(),
                    userAuthDtoOptional.get().getId());

            String refreshTokenNew = jwtTokenProvider.createRefreshToken(username, userAuthDtoOptional.get().getId());

            jwtRefreshTokenService.disableRefreshTokenAfterGenerateNewRefreshToken(refreshToken);

            response.put(USER_NAME, username);
            response.put(ACCESS_TOKEN, accessTokenNew);
            response.put(REFRESH_TOKEN, refreshTokenNew);
        }
    }
}
