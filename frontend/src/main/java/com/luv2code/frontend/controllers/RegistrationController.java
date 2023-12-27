package com.luv2code.frontend.controllers;

import java.util.logging.Logger;

import com.luv2code.frontend.dto.WebUser;
import com.luv2code.frontend.entities.User;
import com.luv2code.frontend.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * Controller per la gestione della registrazione di un utente
 */
@Controller
@RequestMapping("register")
public class RegistrationController {

    /**
     * Logger
     */
    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Servizio per il salvataggio di un utente a DB
     */
    private UserService userService;

    /**
     * Costruttore parametrico
     * @param userService Servizio per il salvataggio di un utente a DB
     */
    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Esegui il trim dei campi prima del processamento della request
     * @param dataBinder binder
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    /**
     * Visualizza la vista form di registrazione
     * @return Vista form di registrazione
     */
    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model model) {
        model.addAttribute("webUser", new WebUser());

    return "register/registration-form";
    }

    /**
     * Registra un utente
     * @param theWebUser Utente da registrare
     * @param theBindingResult Risultati binding
     * @param session Sessione
     * @param theModel Modello
     * @return Vista conferma registrazione o form di registrazione
     */
    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("webUser") WebUser theWebUser
            , BindingResult theBindingResult, HttpSession session, Model theModel) {

        String userName = theWebUser.getUserName();
        logger.info("Processing registration form for: " + userName);

        if (theBindingResult.hasErrors()){
            return "register/registration-form";
        }

        User existing = userService.findByUserName(userName);
        if (existing != null) {
            theModel.addAttribute("webUser", new WebUser());
            theModel.addAttribute("registrationError", "User name already exists.");

            logger.warning("User name already exists.");
            return "register/registration-form";
        }

        userService.save(theWebUser);
        session.setAttribute("user", theWebUser);
        logger.info("Successfully created user: " + userName);

        return "register/registration-confirmation";
    }
}
