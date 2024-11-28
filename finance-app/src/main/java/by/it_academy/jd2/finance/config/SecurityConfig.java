package by.it_academy.jd2.finance.config;

import by.it_academy.jd2.finance.controller.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter filter) throws Exception {
        // Disable CORS and CSRF
        http.cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable);

        // Set session management to stateless
        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Set unauthorized requests exception handler
        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                httpSecurityExceptionHandlingConfigurer
                        .authenticationEntryPoint((request, response, authException) ->
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
                        )
                        .accessDeniedHandler((request, response, authException) ->
                                response.setStatus(HttpServletResponse.SC_FORBIDDEN)
                        )
        );

        // Set permissions on endpoints
        http.authorizeHttpRequests(requests -> requests
                // Our authenticated endpoints
                .requestMatchers("/users", "users/**").hasAnyRole("ADMIN")
                .requestMatchers("/audit", "audit/**").hasAnyRole("ADMIN")
                .requestMatchers("/cabinet/login").permitAll()
                .requestMatchers("/cabinet/verification").permitAll()
                .requestMatchers("/cabinet/registration").permitAll()
                .anyRequest().authenticated()
        );

        // Add JWT token filter
        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}