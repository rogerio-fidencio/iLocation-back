package br.com.verbososcorp.ilocation.controller;

import static br.com.verbososcorp.ilocation.util.Project.BASE_URL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.verbososcorp.ilocation.exceptions.customExceptions.InternalServerErrorException;
import br.com.verbososcorp.ilocation.models.Customer;
import br.com.verbososcorp.ilocation.services.interfaces.CustomerService;

@RestController
@RequestMapping(BASE_URL + "/customer")
public class CustomerController {

    @Autowired
    CustomerService service;

    @GetMapping("")
    public ResponseEntity<List<Customer>> getall(){
        try {
			return ResponseEntity.ok(service.getall());
			
		} catch (Exception e) {
			
			throw new InternalServerErrorException(e.getMessage());
			
		}
    }
}
