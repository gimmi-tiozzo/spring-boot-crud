package com.luv2code.backend.controllers;

import com.luv2code.backend.dto.EmployeeErrorResponse;
import com.luv2code.backend.exceptions.EmployeeDataNotValidException;
import com.luv2code.backend.exceptions.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Intercetta le eccezioni lanciate dal controller Rest e gestiscile come risposte ad hoc Http
 */
@ControllerAdvice
public class EmployeeRestExceptionHandler {

    /**
     * Dipendente non trovato. Errore 404
     * @param exception Eccezione in caso di dipendente di un azienda non trovato
     * @return Risposta Http 404
     */
    @ExceptionHandler
    ResponseEntity<EmployeeErrorResponse> handleEmployeeNotFound(EmployeeNotFoundException exception) {
        EmployeeErrorResponse responseBody = new EmployeeErrorResponse();
        responseBody.setMessage(exception.getMessage());
        responseBody.setStatus(HttpStatus.NOT_FOUND.value());
        responseBody.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<EmployeeErrorResponse>(responseBody, HttpStatus.NOT_FOUND);
    }

    /**
     * Dipendente bad request. Errore 400
     * @param exception Eccezione in caso di dati non validi relativi all'entità dipendente di un azienda
     * @return Risposta Http 400
     */
    @ExceptionHandler
    ResponseEntity<EmployeeErrorResponse> handleEmployeeDataNotValid(EmployeeDataNotValidException exception) {
        EmployeeErrorResponse responseBody = new EmployeeErrorResponse();
        responseBody.setMessage(exception.getMessage());
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        responseBody.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<EmployeeErrorResponse>(responseBody, HttpStatus.BAD_REQUEST);
    }
}
