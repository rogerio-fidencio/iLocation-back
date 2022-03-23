package br.com.verbososcorp.ilocation.controller;


import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import br.com.verbososcorp.ilocation.services.Impl.DeliveryPersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static br.com.verbososcorp.ilocation.util.Project.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/deliveryperson")
public class DeliveryPersonController {

    @Autowired
    DeliveryPersonServiceImpl service;

    @PostMapping()
    public ResponseEntity<DeliveryPerson> getAll(@Validated @RequestBody DeliveryPerson deliveryPerson){
        return service.register(deliveryPerson);
    }
}
