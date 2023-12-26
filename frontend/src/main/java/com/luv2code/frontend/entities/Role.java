package com.luv2code.frontend.entities;

import jakarta.persistence.*;

/**
 * Entità che rappresenta un ruolo associabile a uno user
 */
@Entity
@Table(name = "role")
public class Role {

    /**
     * Id che identifica un ruolo
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Nome ruolo
     */
    @Column(name = "name")
    private String name;

    /**
     * Costruttore di default
     */
    public Role() {
    }

    /**
     * Costruttore parametrico
     * @param id Id che identifica un ruolo
     * @param name Nome ruolo
     */
    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Ottieni Id che identifica un ruolo
     * @return Id che identifica un ruolo
     */
    public int getId() {
        return id;
    }

    /**
     * Imposta Id che identifica un ruolo
     * @param id Id che identifica un ruolo
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Ottieni il Nome ruolo
     * @return Nome ruolo
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il Nome ruolo
     * @param name Nome ruolo
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Ottieni la rappresentazione in stringa dell'oggetto dipendente di una azienda
     * @return Rappresentazione in stringa dell'oggetto dipendente di una azienda
     */
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
