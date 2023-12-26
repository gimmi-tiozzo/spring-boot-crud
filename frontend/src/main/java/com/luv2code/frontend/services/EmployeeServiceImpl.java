package com.luv2code.frontend.services;

import com.luv2code.frontend.entities.Employee;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service relativo ai metodi di accesso CRUD al database dei dipendenti di una azienda
 */
@Service("employeeServiceImpl")
@Primary
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * Trova un dipendente di una azienda a partire dal suo nome
     * @param firstName Nome del dipendente di una azienda
     * @return dipendente di una azienda trovato a partire dal suo nome
     */
    @Override
    public Employee findByFirstName(String firstName) {
        return null;
    }

    /**
     * Trova un dipendente di una azienda a partire dal suo ID
     * @param id ID del dipendente di una azienda
     * @return dipendente di una azienda trovato a partire dal suo ID
     */
    @Override
    public Employee findById(int id) {
        return null;
    }

    /**
     * Trova tutti i dipendenti di una azienda
     * @return Tutti i dipendenti di una azienda
     */
    @Override
    public List<Employee> findAll() {
        return null;
    }

    /**
     * Inserisci un dipendente di una azienda nel database
     * @param employee Dipendente di una azienda
     * @return Dipendente di una azienda inserito
     */
    @Override
    public Employee insert(Employee employee) {
        return null;
    }

    /**
     * Aggiorna un dipendente di una azienda nel database
     * @param employee Dipendente di una azienda
     */
    @Override
    public void update(Employee employee) {

    }

    /**
     * Cancella il dipendente di una azienda a partire dal suo ID
     * @param id ID del dipendente di una azienda
     */
    @Override
    public void deleteById(int id) {

    }
}
