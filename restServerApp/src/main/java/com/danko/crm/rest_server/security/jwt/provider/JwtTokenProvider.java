package com.danko.crm.rest_server.security.jwt.provider;

import com.danko.crm.rest_server.security.jwt.exception.JwtAuthenticationException;
import com.danko.crm.service.JwtRefreshTokenService;
import com.danko.crm.service.dto.RoleDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {
    private static final String USER_ID = "id";
    private static final String TOKEN_EXPIRED_EXCEPTION_MESSAGE="JWT token is expired or invalid";
    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    @Value("${jwt.token.refresh.expired}")
    private long validityInMillisecondsRefreshToken;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRefreshTokenService jwtRefreshTokenService;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String username, List<RoleDto> roles, Long id) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put(USER_ID, id);
        claims.put("roles", getRoleNames(roles));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String createRefreshToken(String username, Long userId) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(USER_ID, userId);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMillisecondsRefreshToken);

        String refreshToken = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        jwtRefreshTokenService.saveToken(refreshToken, userId);

        return refreshToken;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException(TOKEN_EXPIRED_EXCEPTION_MESSAGE);
        }
    }

    private List<String> getRoleNames(List<RoleDto> userRoles) {
        List<String> result = new ArrayList<>();

        userRoles.forEach(role -> {
            result.add(role.getName());
        });

        return result;
    }
}
