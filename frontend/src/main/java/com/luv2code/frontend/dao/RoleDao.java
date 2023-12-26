package com.luv2code.frontend.dao;

import com.luv2code.frontend.entities.Role;

/**
 * Dao per operazioni a DB per Entity Role
 */
public interface RoleDao {

    /**
     * Trova un ruolo in base al nome
     * @param theRoleName Nome del ruolo
     * @return Ruolo trovato in base al nome
     */
    Role findRoleByName(String theRoleName);
}
