package com.arquitectura.proyecto.config.filters;

import com.arquitectura.proyecto.config.provider.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Arrays;

@Log4j2
@Configuration
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    // Definir las rutas que no requieren autenticación
    public static final String[] AUTH_WHITELIST = {
        "/login", "/logout",
        "/graphiql", "/graphiql/*", "/graphiql/**",
        "/swagger-ui.html", "/swagger-ui/**",
        "/v3/api-docs", "/v3/api-docs/swagger-config"
    };
    

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       // Verificar si la ruta está en la lista blanca
       if (Arrays.stream(AUTH_WHITELIST).anyMatch(path -> request.getServletPath().startsWith(path))) {
           filterChain.doFilter(request, response);
           return;
       }

       // Obtener el header de autorización
       String authHeader = request.getHeader("Authorization");

       if (authHeader != null && authHeader.startsWith("Bearer ")) {
           try {
               // Extraer el token del header
               String token = authHeader.substring("Bearer ".length());
               if (!this.jwtTokenProvider.validateToken(token)) {
                   response.setStatus(HttpStatus.UNAUTHORIZED.value());
                   response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                   new ObjectMapper().writeValue(response.getOutputStream(), Collections.singletonMap("error_message", "El header de autorización no es válido o no existe."));
                   return;
               }
               // Si el token es válido, obtener la autenticación
               SecurityContextHolder.getContext().setAuthentication(this.jwtTokenProvider.getUsernameAndPasswordAuth(token));
               filterChain.doFilter(request, response);
           } catch (IllegalArgumentException e) {
               log.error("Error procesando token JWT: {}", e.getMessage());
               response.setStatus(HttpStatus.UNAUTHORIZED.value());
               response.setContentType(MediaType.APPLICATION_JSON_VALUE);
               new ObjectMapper().writeValue(response.getOutputStream(), Collections.singletonMap("error_message", "Token JWT inválido"));
           }
       } else {
           response.setStatus(HttpStatus.UNAUTHORIZED.value());
           response.setContentType(MediaType.APPLICATION_JSON_VALUE);
           new ObjectMapper().writeValue(response.getOutputStream(), Collections.singletonMap("error_message", "El header de autorización no es válido o no existe."));
       }
    }
}
