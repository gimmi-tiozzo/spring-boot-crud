package com.luv2code.frontend.dao;

import com.luv2code.frontend.entities.User;

/**
 * Dao per operazione a DB per Entity User
 */
public interface UserDao {
    /**
     * Trova uno user in base alla username
     * @param userName Username
     * @return User trovato n base alla username
     */
    User findByUserName(String userName);
}
