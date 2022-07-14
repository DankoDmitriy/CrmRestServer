package com.danko.crm.rest_server.security.jwt.filter;

import com.danko.crm.rest_server.security.jwt.exception.JwtAuthenticationException;
import com.danko.crm.rest_server.security.jwt.provider.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        if (!request.getRequestURL().toString().contains("server/api/auth/login")
                || !request.getRequestURL().toString().contains("server/api/auth/refresh")) {

            String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
            try {
                if (token != null && jwtTokenProvider.validateToken(token)) {
                    Authentication auth = jwtTokenProvider.getAuthentication(token);

                    if (auth != null) {
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
            } catch (Exception e) {
                HttpServletResponse httpServletResponse = (HttpServletResponse) res;
//                FIXME - now we have 401 answer, but we have next exception. If delete lest line, we have 403 answer and dose not have next exception.
                httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
//                httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value()); //FIXME This line
            }
        }
        filterChain.doFilter(req, res);
    }
}