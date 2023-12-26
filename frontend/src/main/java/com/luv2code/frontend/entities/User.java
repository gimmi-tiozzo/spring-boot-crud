package com.luv2code.frontend.entities;

import jakarta.persistence.*;
import java.util.Collection;

/**
 * Utente che può accedere all'applicativo
 */
@Entity
@Table(name = "user")
public class User {

    /**
     * Id che identifica l'utente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Username
     */
    @Column(name = "username")
    private String userName;

    /**
     * Password
     */
    @Column(name = "password")
    private String password;

    /**
     * Email
     */
    @Column(name = "enabled")
    private boolean enabled;

    /**
     * Ruoli associati all'utente
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    /**
     * Costruttore di default
     */
    public User() {
    }

    /**
     * Costruttore parametrico
     * @param userName Username
     * @param password Password
     * @param enabled Indica se l'utente è abilitato
     */
    public User(String userName, String password, boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

    /**
     * Costruttore parametrico
     * @param userName Username
     * @param password Password
     * @param enabled Indica se l'utente è abilitato
     * @param roles Ruoli associati all'utente
     */
    public User(String userName, String password, boolean enabled,
                Collection<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    /**
     * Ottieni Id che identifica l'utente
     * @return Id che identifica l'utente
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta Id che identifica l'utente
     * @param id Id che identifica l'utente
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Ottieni Username
     * @return Username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Imposta Username
     * @param userName Username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Ottieni Password
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta Password
     * @param password Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Ottieni Abilitazione utente
     * @return Abilitazione utente
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Imposta Abilitazione utente
     * @param enabled Abilitazione utente
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Ottieni i Ruoli associati all'utente
     * @return Ruoli associati all'utente
     */
    public Collection<Role> getRoles() {
        return roles;
    }

    /**
     * Imposta i Ruoli associati all'utente
     * @param roles Ruoli associati all'utente
     */
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    /**
     * Ottieni la rappresentazione in stringa dell'oggetto dipendente di una azienda
     * @return Rappresentazione in stringa dell'oggetto dipendente di una azienda
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}
