package com.arquitectura.proyecto.config;


import com.arquitectura.proyecto.config.filters.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.arquitectura.proyecto.service.UsuarioDetailsService;
import com.arquitectura.proyecto.service.UsuarioDetailsService;
@Configuration
@RequiredArgsConstructor
@Log4j2
public class SecurityConfig  {

    private final UsuarioDetailsService userDetailsService;


    public static final String[] AUTH_WHITELIST = {
            "/login",
            "/logout",
            "/graphiql",
            "/graphiql",
            "/graphiql/*",
            "/graphiql/**",
            "/graphql",
            "/graphql",
            "/graphql/*",
            "/graphql/**",
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/swagger-ui/index.css",
            "/swagger-ui/swagger-ui-bundle.js",
            "/swagger-ui/swagger-initializer.js",
            "/swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-ui/swagger-ui.css",
            "/swagger-ui/swagger-initializer.js",
            "/swagger-ui/favicon-32x32.png",
            "/swagger-ui/favicon-64x64.png",
            "/swagger-ui/swagger-config",
            "/v3/api-docs",
            "/v3/api-docs/swagger-config"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.anyRequest().permitAll();
                })

                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .formLogin(form -> {
                    form.usernameParameter("email");
                    form.passwordParameter("password");
                    form.successHandler(new CustomAuthenticationFilter());
                    form.permitAll();
                })
                .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}