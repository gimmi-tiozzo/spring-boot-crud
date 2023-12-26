package com.luv2code.frontend.services;

import com.luv2code.frontend.entities.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Service relativo ai metodi di accesso CRUD al database dei dipendenti di una azienda
 */
@Service("employeeServiceImpl")
@Primary
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * Url di accesso API v1 Operazioni CRUD in anagrafica dipendenti azienda
     */
    @Value("${backend.baseurl}")
    private String baseApiUrl;

    /**
     * Canale di comunicazione Rest con il backend
     */
    private RestTemplate restCall;

    /**
     * Costruttore parametrico
     * @param restCall Canale di comunicazione Rest con il backend
     */
    public EmployeeServiceImpl(RestTemplate restCall) {
        this.restCall = restCall;
    }

    /**
     * Trova un dipendente di una azienda a partire dal suo nome
     * @param firstName Nome del dipendente di una azienda
     * @return dipendente di una azienda trovato a partire dal suo nome
     */
    @Override
    public Employee findByFirstName(String firstName) {
        String url = baseApiUrl + "/search?first-name=" + firstName;
        return restCall.getForObject(url, Employee.class);
    }

    /**
     * Trova un dipendente di una azienda a partire dal suo ID
     * @param id ID del dipendente di una azienda
     * @return dipendente di una azienda trovato a partire dal suo ID
     */
    @Override
    public Employee findById(int id) {
        String url = baseApiUrl + "/" + id;
        return restCall.getForObject(url, Employee.class);
    }

    /**
     * Trova tutti i dipendenti di una azienda
     * @return Tutti i dipendenti di una azienda
     */
    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();

        ResponseEntity<List<Employee>> claimResponse  = restCall.exchange(baseApiUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Employee>>() {});

        if(claimResponse.hasBody()) {
            employees = claimResponse.getBody();
        }

        return employees;
    }

    /**
     * Inserisci un dipendente di una azienda nel database
     * @param employee Dipendente di una azienda
     * @return Dipendente di una azienda inserito
     */
    @Override
    public Employee insert(Employee employee) {
        return restCall.postForObject(baseApiUrl, employee, Employee.class);
    }

    /**
     * Aggiorna un dipendente di una azienda nel database
     * @param employee Dipendente di una azienda
     */
    @Override
    public void update(Employee employee) {
        restCall.put(baseApiUrl, employee);
    }

    /**
     * Cancella il dipendente di una azienda a partire dal suo ID
     * @param id ID del dipendente di una azienda
     */
    @Override
    public void deleteById(int id) {
        String url = baseApiUrl + "/" + id;
        restCall.delete(url);
    }
}
