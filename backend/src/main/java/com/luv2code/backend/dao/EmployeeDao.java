package com.luv2code.backend.dao;

import com.luv2code.backend.entities.Employee;

import java.util.List;

/**
 * Interfaccia per i metodi di accesso CRUD al database dei dipendenti di una azienda
 */
public interface EmployeeDao {

    /**
     * Trova un dipendente di una azienda a partire dal suo nome
     * @param firstName Nome del dipendente di una azienda
     * @return dipendente di una azienda trovato a partire dal suo nome
     */
    Employee findByFirstName(String firstName);

    /**
     * Trova un dipendente di una azienda a partire dal suo ID
     * @param id ID del dipendente di una azienda
     * @return dipendente di una azienda trovato a partire dal suo ID
     */
    Employee findById(int id);

    /**
     * Trova tutti i dipendenti di una azienda
     * @return Tutti i dipendenti di una azienda
     */
    List<Employee> findAll();

    /**
     * Inserisci un dipendente di una azienda nel database
     * @param employee Dipendente di una azienda
     * @return Dipendente di una azienda inserito
     */
    Employee insert(Employee employee);

    /**
     * Aggiorna un dipendente di una azienda nel database
     * @param employee Dipendente di una azienda
     */
    void update(Employee employee);

    /**
     * Cancella il dipendente di una azienda a partire dal suo ID
     * @param id ID del dipendente di una azienda
     */
    void deleteById(int id);
}
