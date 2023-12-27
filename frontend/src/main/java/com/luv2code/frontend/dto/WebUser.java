package com.luv2code.frontend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Utente nelle form della UI
 */
public class WebUser {

    /**
     * Username
     */
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String userName;

    /**
     * Password
     */
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String password;

    /**
     * Nome
     */
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String firstName;

    /**
     * Cognome
     */
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    /**
     * email
     */
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            , message = "email is not valid")
    private String email;

    /**
     * Costruttore di default
     */
    public WebUser() {
    }

    /**
     * Costruttore parametrico
     * @param userName Username
     * @param password Password
     * @param firstName Nome
     * @param lastName Cognome
     * @param email Email
     */
    public WebUser(String userName, String password, String firstName, String lastName, String email) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
     * Ottieni il nome
     * @return Nome
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Imposta il nome
     * @param firstName Nome
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Ottieni il cognome
     * @return Cognome
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Imposta il cognome
     * @param lastName cognome
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Ottieni email
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta email
     * @param email Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Ottieni la rappresentazione in stringa dell'oggetto dipendente di una azienda
     * @return Rappresentazione in stringa dell'oggetto dipendente di una azienda
     */
    @Override
    public String toString() {
        return "WebUser{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
