package br.com.verbososcorp.ilocation.controller;

<<<<<<< HEAD
import static br.com.verbososcorp.ilocation.util.Project.BASE_URL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.verbososcorp.ilocation.DAO.DeliveryPersonDAO;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;
=======

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
>>>>>>> d6728ea99caeecaecb298b3cea15f8a28e58417c

@RestController
@RequestMapping(BASE_URL + "/deliveryperson")
public class DeliveryPersonController {
<<<<<<< HEAD
	
	@Autowired
	private DeliveryPersonDAO dao;
	
	@GetMapping("/getall")
	public List<DeliveryPerson> getall() {
		return (List<DeliveryPerson>)dao.findAll();
	}
	
=======

    @Autowired
    DeliveryPersonServiceImpl service;

    @PostMapping()
    public ResponseEntity<DeliveryPerson> getAll(@Validated @RequestBody DeliveryPerson deliveryPerson){
        return service.register(deliveryPerson);
    }
>>>>>>> d6728ea99caeecaecb298b3cea15f8a28e58417c
}
