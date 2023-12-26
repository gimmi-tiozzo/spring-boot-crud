package com.luv2code.backend.controllers;

import com.luv2code.backend.entities.Employee;
import com.luv2code.backend.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per l'accesso REST alle api CRUD del database dei dipendenti di una azienda
 */
@RestController
@RequestMapping("/api/v1")
public class EmployeeRestController {

    /**
     * Servizio per l'accesso al database dei dipendenti di una azienda
     */
    EmployeeService employeeService;

    /**
     * Costruttore parametrico
     * @param employeeService Servizio per l'accesso al database dei dipendenti di una azienda
     */
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Trova un dipendente di una azienda a partire dal suo nome
     * @param firstName Nome del dipendente di una azienda
     * @return dipendente di una azienda trovato a partire dal suo nome
     */
    @GetMapping("/employees/search")
    public Employee findByFirstName(@RequestParam("first-name") String firstName) {
        return this.employeeService.findByFirstName(firstName);
    }

    /**
     * Trova un dipendente di una azienda a partire dal suo ID
     * @param id ID del dipendente di una azienda
     * @return dipendente di una azienda trovato a partire dal suo ID
     */
    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable("id") int id) {
        return this.employeeService.findById(id);
    }

    /**
     * Trova tutti i dipendenti di una azienda
     * @return Tutti i dipendenti di una azienda
     */
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return this.employeeService.findAll();
    }

    /**
     * Inserisci un dipendente di una azienda nel database
     * @param employee Dipendente di una azienda
     * @return Dipendente di una azienda inserito
     */
    @PostMapping("/employees")
    public Employee insert(@RequestBody Employee employee) {
        return this.employeeService.insert(employee);
    }

    /**
     * Aggiorna un dipendente di una azienda nel database
     * @param employee Dipendente di una azienda
     */
    @PutMapping("/employees")
    public void update(@RequestBody Employee employee) {
        this.employeeService.update(employee);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteById(@PathVariable("id") int id) {
        this.employeeService.deleteById(id);
    }
}
