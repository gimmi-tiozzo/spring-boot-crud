package com.luv2code.frontend.services;

import com.luv2code.frontend.dao.RoleDao;
import com.luv2code.frontend.dao.UserDao;
import com.luv2code.frontend.dto.WebUser;
import com.luv2code.frontend.entities.Role;
import com.luv2code.frontend.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
     * Bcrypt password encoder
     */
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Costruttore parametrico
     * @param userDao Dao per la gestione di User
     * @param roleDao Dao per la gestione di Role
     * @param passwordEncoder Bcrypt password encoder
     */
    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
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
     * Salva uno user a database
     * @param webUser User da salvare
     */
    @Override
    @Transactional
    public void save(WebUser webUser) {
        User user = new User();

        user.setFirstName(webUser.getFirstName());
        user.setLastName(webUser.getLastName());
        user.setEmail(webUser.getEmail());
        user.setUserName(webUser.getUserName());
        user.setPassword(passwordEncoder.encode(webUser.getPassword()));
        user.setEnabled(true);

        user.setRoles(Collections.singletonList(roleDao.findRoleByName("ROLE_EMPLOYEE")));

        userDao.save(user);
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
