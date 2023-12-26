package com.luv2code.frontend.services;

import com.luv2code.frontend.dao.RoleDao;
import com.luv2code.frontend.dao.UserDao;
import com.luv2code.frontend.entities.Role;
import com.luv2code.frontend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Service per accesso agli oggetti DB per gestire la sicurezza
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * Dao per la gestione di User
     */
    private UserDao userDao;

    /**
     * Dao per la gestione di Role
     */
    private RoleDao roleDao;

    /**
     * Costruttore parametrico
     * @param userDao Dao per la gestione di User
     * @param roleDao Dao per la gestione di Role
     */
    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    /**
     * Trova uno user in base al suo username
     * @param userName Username
     * @return User trovato in base al suo username
     */
    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    /**
     * Trova un utente e restituiscilo con le classi di Spring
     * @param username Username
     * @return Utente trovato
     * @throws UsernameNotFoundException Utente non trovato
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = findByUserName(username);

            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        } catch (Exception err) {
            throw new UsernameNotFoundException("Invalid username or password.", err);
        }
    }

    /**
     * Rimappa i ruoli in una collection di SimpleGrantedAuthority
     * @param roles Ruoli associati a User
     * @return Ruoli rimappati in una collection di SimpleGrantedAuthority
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
