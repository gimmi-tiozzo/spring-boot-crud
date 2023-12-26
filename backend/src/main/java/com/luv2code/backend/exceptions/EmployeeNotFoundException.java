package com.luv2code.backend.exceptions;

/**
 * Eccezione in caso di dipendente di una azienda non trovato a DB
 */
public class EmployeeNotFoundException extends RuntimeException {

    /**
     * Costruttore parametrico
     * @param message Messaggio di errore
     */
    public EmployeeNotFoundException(String message) {
        super(message);
    }

    /**
     * Costruttore parametrico
     * @param message Messaggio di errore
     * @param cause causa errore
     */
    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Costruttore parametrico
     * @param cause causa errore
     */
    public EmployeeNotFoundException(Throwable cause) {
        super(cause);
    }
}
