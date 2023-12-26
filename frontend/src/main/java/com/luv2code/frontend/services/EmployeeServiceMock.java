package com.luv2code.frontend.services;

import com.luv2code.frontend.entities.Employee;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service Mock relativo ai metodi di accesso CRUD al database dei dipendenti di una azienda
 */
@Service("employeeServiceMock")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EmployeeServiceMock implements EmployeeService {

    /**
     * Mock lista dipendenti di una azienda
     */
    private List<Employee> employees;

    /**
     * Ottieni la lista dipendenti di una azienda
     * @return Lista dipendenti di una azienda
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Imposta la lista dipendenti di una azienda
     * @param employees lista dipendenti di una azienda
     */
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Costruttore di default
     */
    public EmployeeServiceMock() {
        this.employees = new ArrayList<>();
        this.employees.add(new Employee(1, "Leslie", "Andrews", "leslie@luv2code.com"));
        this.employees.add(new Employee(2, "Emma", "Baumgarten", "emma@luv2code.com"));
        this.employees.add(new Employee(3, "Avani", "Gupta", "avani@luv2code.com"));
        this.employees.add(new Employee(4, "Yuri", "Petrov", "yuri@luv2code.com"));
        this.employees.add(new Employee(5, "Juan", "Vega", "juan@luv2code.com"));
    }

    /**
     * Trova un dipendente di una azienda a partire dal suo nome
     * @param firstName Nome del dipendente di una azienda
     * @return dipendente di una azienda trovato a partire dal suo nome
     */
    @Override
    public Employee findByFirstName(String firstName) {
        Employee employee = null;

        for (Employee emp : getEmployees()) {
            if (emp.getFirstName().equals(firstName)) {
                employee = emp;
                break;
            }
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
        Employee employee = null;

        for (Employee emp : getEmployees()) {
            if (emp.getId() == id) {
                employee = emp;
                break;
            }
        }

        return employee;
    }

    /**
     * Trova tutti i dipendenti di una azienda
     * @return Tutti i dipendenti di una azienda
     */
    @Override
    public List<Employee> findAll() {
        return this.getEmployees();
    }

    /**
     * Inserisci un dipendente di una azienda nel database
     * @param employee Dipendente di una azienda
     * @return Dipendente di una azienda inserito
     */
    @Override
    public Employee insert(Employee employee) {
        int id = 0;

        //calcola id employee
        for (Employee emp : getEmployees()) {
            if (emp.getId() > id) {
                id = emp.getId();
            }
        }

        employee.setId(++id);
        this.getEmployees().add(employee);

        return employee;
    }

    /**
     * Aggiorna un dipendente di una azienda nel database
     * @param employee Dipendente di una azienda
     */
    @Override
    public void update(Employee employee) {
        for (Employee emp : getEmployees()) {
            if (emp.getId() == employee.getId()) {
                emp.setFirstName(employee.getFirstName());
                emp.setLastName(employee.getLastName());
                emp.setEmail(employee.getEmail());
                break;
            }
        }
    }

    /**
     * Cancella il dipendente di una azienda a partire dal suo ID
     * @param id ID del dipendente di una azienda
     */
    @Override
    public void deleteById(int id) {
        for (int i = 0; i < getEmployees().size(); i++) {
            if (getEmployees().get(i).getId() == id) {
                getEmployees().remove(i);
                break;
            }
        }
    }
}
