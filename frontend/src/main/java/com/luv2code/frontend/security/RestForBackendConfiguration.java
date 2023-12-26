package com.luv2code.frontend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

/**
 * Configura il resttemplate per accedere al back-end con autorizzazione basic
 */
@Configuration
public class RestForBackendConfiguration {

    /**
     * Username di accesso API v1 Operazioni CRUD in anagrafica dipendenti azienda
     */
    @Value("${backend.username}")
    private String username;

    /**
     * Password di accesso API v1 Operazioni CRUD in anagrafica dipendenti azienda
     */
    @Value("${backend.password}")
    private String password;

    /**
     * Ottieni un RestTemplate con Basic Authorization
     * @return RestTemplate con Basic Authorization
     */
    @Bean
    public RestTemplate getRestTemplateWithBasicAuthorization() {
        RestTemplate restCall = new RestTemplate();
        restCall.getInterceptors().add(new BasicAuthenticationInterceptor(username, password));

        return restCall;
    }
}
