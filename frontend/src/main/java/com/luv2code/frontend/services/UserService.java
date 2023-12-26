package com.luv2code.frontend.services;

import com.luv2code.frontend.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Service per accesso agli oggetti DB per gestire la sicurezza
 */
public interface UserService extends UserDetailsService {
    /**
     * Trova uno user in base al suo username
     * @param userName Username
     * @return User trovato in base al suo username
     */
    User findByUserName(String userName);
}
