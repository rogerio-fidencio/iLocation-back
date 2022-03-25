package br.com.verbososcorp.ilocation.controller;

import static br.com.verbososcorp.ilocation.util.Project.BASE_URL;

import java.util.List;

import br.com.verbososcorp.ilocation.DTO.DeliveryPersonAssignmentDTO;
import br.com.verbososcorp.ilocation.DTO.OrderChangeStatusFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
	
	@GetMapping("/Status")
	public ResponseEntity<List<Order>> getByStatus(@PathVariable Integer status){
		return service.getByStatus(status);
	}

	@PatchMapping("/status")
	public ResponseEntity<Order> changeStatus(@RequestBody @Validated OrderChangeStatusFormDTO changeStatusForm){
		return service.changeStatus(changeStatusForm);
	}
	
	@PatchMapping("/assign/{orderID}")
	public ResponseEntity<Order> assignDeliveryPerson(@PathVariable Integer orderID){
		return service.assignDeliveryPerson(orderID);
	}
}
