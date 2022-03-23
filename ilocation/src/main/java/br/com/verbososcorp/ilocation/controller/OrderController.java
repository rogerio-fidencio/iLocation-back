package br.com.verbososcorp.ilocation.controller;

import static br.com.verbososcorp.ilocation.util.Project.BASE_URL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.verbososcorp.ilocation.models.GeoLocation;
import br.com.verbososcorp.ilocation.models.Order;
import br.com.verbososcorp.ilocation.services.interfaces.OrderService;

@RestController
@RequestMapping(BASE_URL + "/order")
public class OrderController {

	@Autowired
	private OrderService service;
				
	@GetMapping("")
	public ResponseEntity<List<Order>> getAll(){			
		return service.getAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Order> getByID(@PathVariable Integer id){
		return service.getByID(id);
	}
	
	@GetMapping("/getTracking/{id}")
	public ResponseEntity<List<GeoLocation>> getTracking(@PathVariable Integer id){
		return service.getTracking(id);
	}
	
	@GetMapping("/getByStatus/{status}")
	public ResponseEntity<List<Order>> getByStatus(@PathVariable Integer status){
		return service.getByStatus(status);
	}
}
