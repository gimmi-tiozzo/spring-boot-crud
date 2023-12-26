package com.luv2code.backend.entities;

import jakarta.persistence.*;

/**
 * Entità che rappresenta un dipendente di una azienda
 */
@Entity
@Table(name = "employee")
public class Employee {

    /**
     * Id identificativo
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Nome dipendente
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Cognome dipendente
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Email dipendente
     */
    @Column(name = "email")
    private String email;

    /**
     * Costruttore di default
     */
    public Employee() {
    }

    /**
     * Costruttore parametrico
     * @param id Id identificativo
     * @param firstName Nome dipendente
     * @param lastName Cognome dipendente
     * @param email Email dipendente
     */
    public Employee(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Ottieni Id del dipendente di una azienda
     * @return Id del dipendente di una azienda
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta Id del dipendente di una azienda
     * @param id Id del dipendente di una azienda
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Ottieni il nome del dipendente
     * @return Nome del dipendente
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Imposta il nome del dipendente
     * @param firstName Nome del dipendente
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Ottieni il cognome del dipendente
     * @return Cognome del dipendente
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Imposta il cognone del dipendente
     * @param lastName Cognone del dipendente
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Ottieni l'email del dipendente
     * @return Email dipendente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta email del dipendente
     * @param email email del dipendente
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
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
