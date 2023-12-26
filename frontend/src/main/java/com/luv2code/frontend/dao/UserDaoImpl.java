package com.luv2code.frontend.dao;

import com.luv2code.frontend.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Dao per operazione a DB per Entity User
 */
@Repository
public class UserDaoImpl implements UserDao {
    /**
     * Entity Manager JPA di accessso al DB
     */
    private EntityManager entityManager;

    /**
     * Costruttore parametrico
     * @param entityManager Entity Manager JPA di accessso al DB
     */
    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Trova uno user in base alla username
     * @param userName Username
     * @return User trovato n base alla username
     */
    @Override
    public User findByUserName(String userName) {
        TypedQuery<User> query = this.entityManager.createQuery("FROM User WHERE userName = :userName", User.class);
        query.setParameter("userName", userName);

        return query.getSingleResult();
    }
}
