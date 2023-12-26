package com.luv2code.backend.dto;

/**
 * Dto che rappresenta una risposta Json di errore
 */
public class EmployeeErrorResponse {

    /**
     * Codice di stato Http
     */
    private int status;

    /**
     * Messaggio di errore
     */
    private String message;

    /**
     * Timestamp errore
     */
    private long timeStamp;

    /**
     * Costruttore di default
     */
    public EmployeeErrorResponse() {
    }

    /**
     * Costruttore parametrico
     * @param status Codice di stato Http
     * @param message Messaggio di errore
     * @param timeStamp Timestamp errore
     */
    public EmployeeErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    /**
     * Ottieni il codice di stato Http
     * @return Codice di stato Http
     */
    public int getStatus() {
        return status;
    }

    /**
     * Imposta il codice di stato Http
     * @param status Codice di stato Http
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Ottieni il messaggio di errore
     * @return Messaggio di errore
     */
    public String getMessage() {
        return message;
    }

    /**
     * Imposta il messaggio di errore
     * @param message Messaggio di errore
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Ottieni il timestamp errore
     * @return Timestamp errore
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * Imposta il timestamp errore
     * @param timeStamp Timestamp errore
     */
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Ottieni la rappresentazione in stringa dell'oggetto dipendente di una azienda
     * @return Rappresentazione in stringa dell'oggetto dipendente di una azienda
     */
    @Override
    public String toString() {
        return "EmployeeErrorResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
