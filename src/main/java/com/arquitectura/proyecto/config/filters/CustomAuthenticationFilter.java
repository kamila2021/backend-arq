package com.arquitectura.proyecto.config.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.arquitectura.proyecto.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Log4j2
@Component
public class CustomAuthenticationFilter implements AuthenticationSuccessHandler {

    String SECRET_KEY = "secretkey";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, jakarta.servlet.FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Usuario user = new Usuario();

        user.setEmail(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());

        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
        String access_token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", userDetails.getAuthorities().stream().map(authority -> authority.getAuthority()).toList())
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        response.setHeader("access_token", access_token);
        response.setHeader("refresh_token", refresh_token);
    }
}