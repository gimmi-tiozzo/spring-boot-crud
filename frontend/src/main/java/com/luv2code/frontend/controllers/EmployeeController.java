package com.luv2code.frontend.controllers;

import com.luv2code.frontend.entities.Employee;
import com.luv2code.frontend.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per gestire le operazioni CRUD relativi ai dipendenti di una azienda
 */
@Controller
@RequestMapping("employees")
public class EmployeeController {

    /**
     * Service relativo ai metodi di accesso CRUD al database dei dipendenti di una azienda
     */
    private EmployeeService employeeService;

    /**
     * Costruttore parametrico
     * @param employeeService Service relativo ai metodi di accesso CRUD al database dei dipendenti di una azienda
     */
    @Autowired
    public EmployeeController(@Qualifier("employeeServiceMock") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Esegui il trim dei campi prima del processamento della request
     * @param webDataBinder binder
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    /**
     * Visualizza la vista in cui sono elencati i dipendenti di una azienda
     * @param model Model
     * @return vista lista dipendenti
     */
    @GetMapping("/list")
    public String list(Model model) {
        List<Employee> employees = this.employeeService.findAll();
        model.addAttribute("employees", employees);

        return "employees/employee-list";
    }

    /**
     * Visualizza la vista per aggiungere un dipendente di una azienda
     * @param model Modello
     * @return Vista form dipendente
     */
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    /**
     * Salva (insert or update) un dipendente di una azienda
     * @param employee Model per il dipendente di una azienda
     * @param bindingResult Binding per il check validità modello
     * @return Vista lista dipendenti
     */
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Employee employee, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "employees/employee-form";
        }

        if (employee.getId() == 0) {
            this.employeeService.insert(employee);
        } else {
            this.employeeService.update(employee);
        }

        return "redirect:/employees/list";
    }

    /**
     * Visualizza la vista per aggiornare un dipendente di una azienda
     * @param model Modello
     * @return Vista form dipendente
     */
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") int id, Model model) {
        Employee employee = this.employeeService.findById(id);
        model.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        this.employeeService.deleteById(id);

        return "redirect:/employees/list";
    }
}
