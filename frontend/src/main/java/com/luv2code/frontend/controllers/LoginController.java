package com.luv2code.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller per la gestione della fase di login
 */
@Controller
public class LoginController {

    /**
     * Naviga alla vista login
     * @return Vista login
     */
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "login";
    }

    /**
     * Naviga alla vista accesso negato
     * @return Vista accesso negato
     */
    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
