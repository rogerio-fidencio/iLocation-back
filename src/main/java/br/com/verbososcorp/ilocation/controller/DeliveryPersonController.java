package br.com.verbososcorp.ilocation.controller;


import br.com.verbososcorp.ilocation.exceptions.customExceptions.InternalServerErrorException;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import br.com.verbososcorp.ilocation.services.interfaces.DeliveryPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.verbososcorp.ilocation.util.Project.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/deliveryperson")
public class DeliveryPersonController {

    @Autowired
    DeliveryPersonService service;

    @PutMapping()
    public ResponseEntity<DeliveryPerson> putDeliveryPerson(@Validated @RequestBody DeliveryPerson deliveryPerson) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.register(deliveryPerson));
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}