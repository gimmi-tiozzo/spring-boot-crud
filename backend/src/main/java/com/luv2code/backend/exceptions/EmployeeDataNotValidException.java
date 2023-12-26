package com.luv2code.backend.exceptions;

/**
 * Eccezione che indica un dipendente di una azienda non valido in alcuni dei suoi dati
 */
public class EmployeeDataNotValidException extends RuntimeException {

    /**
     * Costruttore parametrico
     * @param message Messaggio di errore
     */
    public EmployeeDataNotValidException(String message) {
        super(message);
    }

    /**
     * Costruttore parametrico
     * @param message Messaggio di errore
     * @param cause causa errore
     */
    public EmployeeDataNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Costruttore parametrico
     * @param cause causa errore
     */
    public EmployeeDataNotValidException(Throwable cause) {
        super(cause);
    }
}
