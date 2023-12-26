package com.luv2code.backend.services;

import com.luv2code.backend.dao.EmployeeDao;
import com.luv2code.backend.entities.Employee;
import com.luv2code.backend.exceptions.EmployeeDataNotValidException;
import com.luv2code.backend.exceptions.EmployeeNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * Dao per accesso CRUD a database dipendenti di una azienda
     */
    private EmployeeDao employeeDao;

    /**
     * Costruttore parametrico
     * @param employeeDao Dao per accesso CRUD a database dipendenti di una azienda
     */
    @Autowired
    public void EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     * Trova un dipendente di una azienda a partire dal suo nome
     * @param firstName Nome del dipendente di una azienda
     * @return dipendente di una azienda trovato a partire dal suo nome
     */
    @Override
    public Employee findByFirstName(String firstName) {
        Employee employee = this.employeeDao.findByFirstName(firstName);

        if (employee == null) {
            throw new EmployeeNotFoundException("Dipendente non trovato. firstName = " + firstName);
        }

        return employee;
    }

    /**
     * Trova un dipendente di una azienda a partire dal suo ID
     * @param id ID del dipendente di una azienda
     * @return dipendente di una azienda trovato a partire dal suo ID
     */
    @Override
    public Employee findById(int id) {
        Employee employee = this.employeeDao.findById(id);

        if (employee == null) {
            throw new EmployeeNotFoundException("Dipendente non trovato. Id = " + id);
        }

        return employee;
    }

    /**
     * Trova tutti i dipendenti di una azienda
     * @return Tutti i dipendenti di una azienda
     */
    @Override
    public List<Employee> findAll() {
        List<Employee> employees = this.employeeDao.findAll();

        if (employees != null && !employees.isEmpty()) {
            return employees;
        }

        throw new EmployeeNotFoundException("Nessun dipendente trovato a database");
    }

    /**
     * Inserisci un dipendente di una azienda nel database
     * @param employee Dipendente di una azienda
     * @return Dipendente di una azienda inserito
     */
    @Override
    @Transactional
    public Employee insert(Employee employee) {
        if (employee == null) {
            throw new EmployeeDataNotValidException("L'entità relativa al dipendente risulta nulla");
        }

        if (employee.getId() > 0) {
            throw new EmployeeDataNotValidException("Id del dipendente di una azienda non deve " +
                    "essere indicato in fase di insert. Id = " + employee.getId());
        }

        this.employeeDao.insert(employee);

        return employee;
    }

    /**
     * Aggiorna un dipendente di una azienda nel database
     * @param employee Dipendente di una azienda
     */
    @Override
    @Transactional
    public void update(Employee employee) {
        if (employee == null) {
            throw new EmployeeDataNotValidException("L'entità relativa al dipendente risulta nulla");
        }

        if (employee.getId() <= 0) {
            throw new EmployeeDataNotValidException("Id del dipendente di una azienda deve essere maggiore di zero. " +
                    "Id = " + employee.getId());
        }

        this.employeeDao.update(employee);
    }

    /**
     * Cancella il dipendente di una azienda a partire dal suo ID
     * @param id ID del dipendente di una azienda
     */
    @Override
    @Transactional
    public void deleteById(int id) {
        Employee employee = findById(id);

        if (employee == null) {
            throw new EmployeeNotFoundException("Dipendente non trovato. Id = " + id);
        }

        this.employeeDao.deleteById(id);
    }
}
