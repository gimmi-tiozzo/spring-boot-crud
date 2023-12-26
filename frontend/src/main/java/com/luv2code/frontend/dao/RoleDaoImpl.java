package com.luv2code.frontend.dao;

import com.luv2code.frontend.entities.Role;
import com.luv2code.frontend.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Dao per operazioni a DB per Entity Role
 */
@Repository
public class RoleDaoImpl implements RoleDao{
    /**
     * Entity Manager JPA di accessso al DB
     */
    private EntityManager entityManager;

    /**
     * Costruttore parametrico
     * @param entityManager Entity Manager JPA di accessso al DB
     */
    @Autowired
    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Trova un ruolo in base al nome
     * @param theRoleName Nome del ruolo
     * @return Ruolo trovato in base al nome
     */
    @Override
    public Role findRoleByName(String theRoleName) {
        TypedQuery<Role> query = this.entityManager.createQuery("FROM Role WHERE name = :name", Role.class);
        query.setParameter("name", theRoleName);

        return query.getSingleResult();
    }
}
