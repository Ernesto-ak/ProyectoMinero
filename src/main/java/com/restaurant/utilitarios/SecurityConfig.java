package com.restaurant.utilitarios; // O tu paquete config

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/login", "/css/**", "/js/**", "/error").permitAll()
	            .requestMatchers("/web/usuarios/**", "/web/roles/**").hasAuthority("Administrador")
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	            .loginPage("/login")
	            .defaultSuccessUrl("/web/autores", true)
	            .permitAll()
	        )
	        .headers(headers -> headers
	            .cacheControl(cache -> cache.disable())
	            .addHeaderWriter((request, response) -> {
	                response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
	                response.setHeader(HttpHeaders.PRAGMA, "no-cache");
	                response.setHeader(HttpHeaders.EXPIRES, "0");
	            })
	        )
	        .logout(logout -> logout
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/login?logout")
	            .invalidateHttpSession(true)  
	            .clearAuthentication(true)   
	            .deleteCookies("JSESSIONID")
	            .permitAll()
	        )
	        .exceptionHandling(ex -> ex
	            .accessDeniedHandler(accessDeniedHandler())
	        );

	    return http.build();
	}

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect(request.getContextPath() + "/web/autores");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}