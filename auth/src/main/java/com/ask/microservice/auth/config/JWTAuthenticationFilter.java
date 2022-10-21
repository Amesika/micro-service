package com.ask.microservice.auth.config;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ask.microservice.auth.entities.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

        private AuthenticationManager authenticationManager;

        public JWTAuthenticationFilter(AuthenticationManager authManager){
            super();
            this.authenticationManager = authManager;
        }
        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
                throws AuthenticationException {
            AppUser user = null;
            try{
                user = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        }

        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                FilterChain chain, Authentication authResult) throws IOException, ServletException {
            
                    User springUser = (User) authResult.getPrincipal();

                    String jwtToken = Jwts.builder()
                    .setSubject(springUser.getUsername())
                    .setExpiration(
                        new Date(System.currentTimeMillis()+SecurityConstant.EXPIRATION_TIME)
                    )
                    .signWith(SignatureAlgorithm.HS512, SecurityConstant.SECRET)
                    .claim("roles", springUser.getAuthorities())
                    .compact();
                    response.addHeader(SecurityConstant.HEADER_STRING,SecurityConstant.TOKEN_PREF_STRING+jwtToken);
        }
}
