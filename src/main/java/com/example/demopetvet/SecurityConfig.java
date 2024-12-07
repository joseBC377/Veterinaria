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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
    //para que sera lo de ariba ver mas adelante
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                    auth -> auth.requestMatchers("/", "/login", "/index", "/about", "/contactanos", "/servicios",
                                    "/productos_gatos", "/productos_perros", "/producto_otras_mascotas",
                                    "/productos","pedido", "/registro", "/intranet_usuario", "/css/**", "/guardado/**",
                                    "/guardadoServicio/**", "/javascript/**", "/Multimedia/**")
                                    .permitAll()
                                    .requestMatchers("/intranet", "/clientes", "/producto", "/categoria", "/servicio", "/reserva",
                                    "/cliente_form","/categoria_form","/producto_form","/contacto_form", "/reserva_form","/servicio_form").hasRole("ADMIN")
                                    .anyRequest().authenticated())
                .exceptionHandling(
                        ex -> ex.accessDeniedPage("/error403"))
                .formLogin(
                        login -> login.loginPage("/intranet_usuario")
                                .loginProcessingUrl("/login")
                                .failureUrl("/intranet_usuario?error=true")
                                .permitAll()
                                .successHandler(successHandler())) 
                .logout(
                        logout -> logout.logoutSuccessUrl("/index").permitAll())
                .build();
    }
    

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
          
            if (authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
                response.sendRedirect("/intranet"); 
            } else {
                response.sendRedirect("/perfil"); 
            }
        };
    }
}
