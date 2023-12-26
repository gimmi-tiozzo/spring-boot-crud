package com.luv2code.backend.dao;

import com.luv2code.backend.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementazione Dao per i metodi di accesso CRUD al database dei dipendenti di una azienda
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    /**
     * Entity Manager per l'accesso JPA al database dei dipendenti di una azienda
     */
    private EntityManager entityManager;

    /**
     * Costruttore parametrico
     * @param entityManager Entity Manager per l'accesso JPA al database dei dipendenti di una azienda
     */
    @Autowired
    public void EmployeeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Trova un dipendente di una azienda a partire dal suo nome
     * @param firstName Nome del dipendente di una azienda
     * @return dipendente di una azienda trovato a partire dal suo nome
     */
    @Override
    public Employee findByFirstName(String firstName) {
        TypedQuery<Employee> query = this.entityManager.createQuery("FROM Employee WHERE firstName = :firstName"
                , Employee.class);
        query.setParameter("firstName", firstName);

        return query.getSingleResult();
    }

    /**
     * Trova un dipendente di una azienda a partire dal suo ID
     * @param id ID del dipendente di una azienda
     * @return dipendente di una azienda trovato a partire dal suo ID
     */
    @Override
    public Employee findById(int id) {
       return this.entityManager.find(Employee.class, id);
    }

    /**
     * Trova tutti i dipendenti di una azienda
     * @return Tutti i dipendenti di una azienda
     */
    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = this.entityManager.createQuery("FROM Employee", Employee.class);
        return query.getResultList();
    }

    /**
     * Inserisci un dipendente di una azienda nel database
     * @param employee Dipendente di una azienda
     * @return Dipendente di una azienda inserito
     */
    @Override
    public Employee insert(Employee employee) {
        this.entityManager.persist(employee);
        return employee;
    }

    /**
     * Aggiorna un dipendente di una azienda nel database
     * @param employee Dipendente di una azienda
     */
    @Override
    public void update(Employee employee) {
        this.entityManager.merge(employee);
    }

    /**
     * Cancella il dipendente di una azienda a partire dal suo ID
     * @param id ID del dipendente di una azienda
     */
    @Override
    public void deleteById(int id) {
        Query query = this.entityManager.createQuery("DELETE FROM Employee WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
