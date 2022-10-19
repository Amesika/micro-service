package com.ask.microservice.microservice.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class JWTAuthorizationFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.err.println("*************************");

        String  jwt = request.getHeader("Authorization");
        if(jwt==null) throw new RuntimeException("Not Authorized");
        filterChain.doFilter(request, response);
    }

}
