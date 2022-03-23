package br.com.verbososcorp.ilocation.controller;


import br.com.verbososcorp.ilocation.DAO.CustomerDAO;
import br.com.verbososcorp.ilocation.models.Customer;
import br.com.verbososcorp.ilocation.services.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.verbososcorp.ilocation.util.Project.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/customer")
public class CustomerController {

    @Autowired
    CustomerService service;

    @GetMapping("/getall")
    public ResponseEntity<List<Customer>> getall(){
        return service.getall();
    }
}
