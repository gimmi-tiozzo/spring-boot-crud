package com.luv2code.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Factory per la creazione dei spring bean di configurazione relativi alla sicurezza
 */
@Configuration
public class EmployeeSecurityConfig {

    /**
     * Costruisci bean anagrafica utenti (in memory)
     * @return Bean anagrafica utenti (in memory)
     */
    @Bean
    public UserDetailsManager userDetailsManager()
    {
        UserDetails reader = User.builder()
                .username("reader")
                .password("{noop}test123")
                .roles("READER")
                .build();

        UserDetails manager = User.builder()
                .username("manager")
                .password("{noop}test123")
                .roles("READER", "MANAGER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}test123")
                .roles("READER", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(reader, manager, admin);
    }

    /**
     * Costruisci il bean per la configurazione dei vincoli di sicurezza
     * @param http Contesto di sicurezza
     * @return Bean per la configurazione dei vincoli di sicurezza
     * @throws Exception Eccezione in caso di errore
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(sec -> sec
                        .requestMatchers(HttpMethod.GET, "/actuator/**").hasRole("READER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/employees").hasRole("READER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/employees/**").hasRole("READER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/employees/**").hasRole("ADMIN"));

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
