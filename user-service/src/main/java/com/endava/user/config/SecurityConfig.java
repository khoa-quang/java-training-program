package com.endava.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity; enable in production
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Temporary allow all request without authentication
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
