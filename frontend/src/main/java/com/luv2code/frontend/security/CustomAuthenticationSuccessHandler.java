package com.luv2code.frontend.security;

import com.luv2code.frontend.entities.User;
import com.luv2code.frontend.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Bean attivato in caso di login conclusa con successo
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * Servizio per la gestione delle utenze durante il login e profilazione
     */
    private UserService userService;

    /**
     * Costruttore parametrico
     * @param userService Servizio per la gestione delle utenze durante il login e profilazione
     */
    @Autowired
    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    /**
     * Intercetta ed elabora login eseguita con successo
     * @param request Richiesta http
     * @param response Response http
     * @param chain filtro successivo
     * @param authentication parametri di autenticazione
     * @throws IOException Errori I\O
     * @throws ServletException Errori durante esecuzione Servlet
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain
            , Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    /**
     * Intercetta ed elabora login eseguita con successo
     * @param request Richiesta http
     * @param response Response http
     * @param authentication parametri di autenticazione
     * @throws IOException Errori I\O
     * @throws ServletException Errori durante esecuzione Servlet
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {
        System.out.println("In customAuthenticationSuccessHandler");

        String userName = authentication.getName();
        System.out.println("userName=" + userName);

        User user = this.userService.findByUserName(userName);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        response.sendRedirect(request.getContextPath() + "/employees/list");
    }
}
