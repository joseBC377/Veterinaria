package com.example.demopetvet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demopetvet.service.CustomUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
        @Autowired
        CustomUserService service;

        private UserDetailsService getDetailsService() {
                return service;
        }

        @Bean
        DaoAuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                provider.setUserDetailsService(getDetailsService());
                provider.setPasswordEncoder(passwordEncoder());
                return provider;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http.authorizeHttpRequests(
                                auth -> auth.requestMatchers("/", "/login", "/index", "/about", "/contactanos",
                                                "/servicios",
                                                "/productos_gato", "/productos_perros", "/producto_otras_mascotas",
                                                "/productos", "/css/**", "/javascript/**", "/Multimedia/**").permitAll()
                                                .requestMatchers("/intranet").hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .exceptionHandling(
                                                ex -> ex.accessDeniedPage("/error-403"))
                                .formLogin(
                                                login -> login.loginProcessingUrl("/login")
                                                                .defaultSuccessUrl("/perfil")
                                                                .failureUrl("/?error=true")
                                                                .permitAll())
                                .logout(
                                                logout -> logout.logoutSuccessUrl("/index").permitAll())
                                .build();
        }
}