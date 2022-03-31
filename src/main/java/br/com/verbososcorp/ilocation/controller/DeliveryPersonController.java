package br.com.verbososcorp.ilocation.controller;


import static br.com.verbososcorp.ilocation.util.Project.BASE_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.verbososcorp.ilocation.exceptions.customExceptions.InternalServerErrorException;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import br.com.verbososcorp.ilocation.services.Impl.DeliveryPersonServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping(BASE_URL + "/deliveryperson")
public class DeliveryPersonController {

    @Autowired
    DeliveryPersonServiceImpl service;

    @PostMapping()
    public ResponseEntity<DeliveryPerson> putDeliveryPerson(@Validated @RequestBody DeliveryPerson deliveryPerson){
        try {
			service.register(deliveryPerson);
			return ResponseEntity.status(HttpStatus.CREATED).build();
			
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
			
			
		}
    }
}