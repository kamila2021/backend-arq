package com.arquitectura.proyecto.security;

import com.arquitectura.proyecto.model.Token;
import com.arquitectura.proyecto.model.TokenType;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.arquitectura.proyecto.repository.UsuarioRepository;
import com.arquitectura.proyecto.service.UserDetailsServiceImpl;
import com.arquitectura.proyecto.repository.TokenRepository;
import com.arquitectura.proyecto.service.UserDetailsImpl;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDateTime;
import static java.util.Arrays.stream;

@Configuration
public class JwtTokenProvider {

    private static final Logger log = LogManager.getLogger(JwtTokenProvider.class);

    private final UserDetailsServiceImpl userDetailsService;
    private final UsuarioRepository usuarioRepository;
    private final TokenRepository tokenRepository;

    private static final String SECRET_KEY = "secretkey";

    public JwtTokenProvider(UserDetailsServiceImpl userDetailsService, UsuarioRepository usuarioRepository, TokenRepository tokenRepository) {
        this.userDetailsService = userDetailsService;
        this.usuarioRepository = usuarioRepository;
        this.tokenRepository = tokenRepository;
    }

    public String generateAccessToken(UserDetails user) {
        if (user == null) {
            return null;
        }

        com.auth0.jwt.JWTCreator.Builder builder = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 10000))
                .withClaim("authorities", user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()));

        if (user instanceof UserDetailsImpl) {
            UserDetailsImpl userImpl = (UserDetailsImpl) user;
            builder.withClaim("id", userImpl.getUserId())
                  .withClaim("nombreCompleto", userImpl.getFirstName() + " " + userImpl.getLastName())
                  .withClaim("correo", userImpl.getEmail())
                  .withClaim("roles", userImpl.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList()))
                  .withClaim("opciones", userImpl.getOptions().stream().map(o -> o.toString()).collect(Collectors.toList()))
                  .withClaim("permisos", userImpl.getPermissions().stream().map(p -> p).collect(Collectors.toList()));
        }

        return builder.sign(Algorithm.HMAC256(SECRET_KEY.getBytes()));
    }

    public String generateRefreshToken(UserDetails user) {
        if (user == null) {
            return null;
        }

        try {
            String token = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 10000))
                    .sign(Algorithm.HMAC256(SECRET_KEY.getBytes()));

            if (token != null && !token.isEmpty() && user instanceof UserDetailsImpl) {
                UserDetailsImpl userImpl = (UserDetailsImpl) user;
                var usuario = this.usuarioRepository.getReferenceById(userImpl.getUserId());
                if (usuario != null) {
                    Token refToken = Token.builder()
                        .token(token)
                        .user(usuario)
                        .status(1)
                        .type(TokenType.REFRESH)
                        .createdAt(LocalDateTime.now())
                        .expiresAt(LocalDateTime.now().plusMinutes(30))
                        .build();
                    this.tokenRepository.save(refToken);
                    return token;
                }
            }
            return null;
        } catch (RuntimeException e) {
            log.error("Error generating refresh token", e);
            return null;
        }
    }

    public boolean validateToken(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            
            // Check if it's a refresh token in the database
            var storedToken = tokenRepository.findByTokenAndStatus(token, 1);
            if (storedToken.isPresent()) {
                return true;
            }
            
            // For access tokens, just validate the signature
            return true;
        } catch (RuntimeException ex) {
            log.error("Invalid JWT signature");
            return false;
        }
    }

    public Authentication getUsernameAndPasswordAuth(String token) {
        if (!validateToken(token)) {
            throw new RuntimeException("Invalid JWT signature");
        }

        try {
            DecodedJWT decodedJWT = this.decodeToken(token);
            String username = decodedJWT.getSubject();
            String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            if (roles != null) {
                stream(roles).forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role));
                });
            }
            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Invalid JWT signature");
        }
    }

    private DecodedJWT decodeToken(String token) throws RuntimeException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Invalid JWT signature");
        }
    }

    public HashMap<String, String> refreshTokens(String refreshToken) throws RuntimeException {
        log.info("{} [REFRESHING TOKENS] Loading refresh token and access token.");
        
        // Validate token signature and existence in database
        if (!this.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }
        
        var storedToken = tokenRepository.findByTokenAndStatus(refreshToken, 1)
            .orElseThrow(() -> new RuntimeException("Refresh token not found or invalid"));

        try {
            DecodedJWT decodedJWT = decodeToken(refreshToken);
            String username = decodedJWT.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            
            // Invalidate old refresh token
            storedToken.setStatus(0);
            tokenRepository.save(storedToken);
            
            String newAccessToken = this.generateAccessToken(userDetails);
            String newRefreshToken = this.generateRefreshToken(userDetails);
            
            if (newAccessToken == null || newRefreshToken == null) {
                throw new RuntimeException("Error generating new tokens");
            }

            HashMap<String, String> newTokens = new HashMap<>();
            newTokens.put("access_token", newAccessToken);
            newTokens.put("refresh_token", newRefreshToken);
            return newTokens;
        } catch (RuntimeException ex) {
            throw new RuntimeException("Error refreshing tokens: " + ex.getMessage());
        }
    }

    public Long getUserIdFromToken(String token) throws Exception {
        if (!validateToken(token)) {
            throw new Exception("Invalid JWT signature");
        }

        try {
            DecodedJWT decodedJWT = this.decodeToken(token);
            return decodedJWT.getClaim("id").asLong();
        } catch (Exception ex) {
            throw new Exception("Invalid JWT signature");
        }
    }
} 