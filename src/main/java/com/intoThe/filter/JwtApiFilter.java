package com.intoThe.filter;

import com.intoThe.service.impl.AuthServiceImpl;
import com.intoThe.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtApiFilter extends OncePerRequestFilter {
    private final AuthServiceImpl authService;
    private final JWTUtils jwtUtils;
    public JwtApiFilter(AuthServiceImpl authService, JWTUtils utils){
        this.authService = authService;
        this.jwtUtils = utils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

    }
}
