package br.com.verbososcorp.ilocation.controller;


import static br.com.verbososcorp.ilocation.util.Project.BASE_URL;

import br.com.verbososcorp.ilocation.DTO.OrderDTO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.DeliveryPersonNotFoundException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.ResourceNotFoundException;
import br.com.verbososcorp.ilocation.services.Impl.OrderServiceImpl;
import br.com.verbososcorp.ilocation.services.interfaces.DeliveryPersonService;
import br.com.verbososcorp.ilocation.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.com.verbososcorp.ilocation.exceptions.customExceptions.InternalServerErrorException;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import br.com.verbososcorp.ilocation.services.Impl.DeliveryPersonServiceImpl;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(BASE_URL + "/deliveryperson")
public class DeliveryPersonController {

    @Autowired
    DeliveryPersonService service;

    @PutMapping()
    public ResponseEntity<DeliveryPerson> putDeliveryPerson(@Validated @RequestBody DeliveryPerson deliveryPerson){
        try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.register(deliveryPerson));
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}
    }
}