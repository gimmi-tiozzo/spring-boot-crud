package com.luv2code.frontend.security;

import com.luv2code.frontend.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Gestione vincoli di sicurezza
 */
@Configuration
public class EmployeeSecurity {


    /**
     * Definizione bean per la configurazione uso bcrypt
     * @return bean per la configurazione uso bcrypt
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Definizione bean per accesso a user/roles
     * @param userService Service per accesso agli oggetti DB per gestire la sicurezza
     * @return Bean per accesso a user/roles
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    /**
     * Configura il filtro per gestire i vincoli di sicurezza
     * @param http Contesto Http
     * @param customAuthenticationSuccessHandler Bean attivato in caso di login conclusa con successo
     * @return Filtro per gestire i vincoli di sicurezza
     * @throws Exception In caso di errori
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationSuccessHandler
            customAuthenticationSuccessHandler) throws Exception {
        http.authorizeHttpRequests(sec -> sec
                .requestMatchers(HttpMethod.GET, "/favicon.ico").permitAll()
                .requestMatchers(HttpMethod.GET, "/access-denied").permitAll()
                .requestMatchers(HttpMethod.GET, "/index.html").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/employees/list").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/employees/showFormForAdd").hasRole("MANAGER")
                .requestMatchers(HttpMethod.GET, "/employees/showFormForUpdate").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/employees/save").hasRole("MANAGER")
                .requestMatchers(HttpMethod.GET, "/employees/delete/**").hasRole("ADMIN")
                .requestMatchers("/register/**").permitAll()
        ).formLogin(form -> form
                        .loginPage("/showMyLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .successHandler(customAuthenticationSuccessHandler)
                        .permitAll()
        ).logout(LogoutConfigurer::permitAll
        ).exceptionHandling(sec -> sec.accessDeniedPage("/access-denied"));

        return http.build();
    }
}
