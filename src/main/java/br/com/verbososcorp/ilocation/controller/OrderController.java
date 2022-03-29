package br.com.verbososcorp.ilocation.controller;

import br.com.verbososcorp.ilocation.DTO.OrderDTO;
import br.com.verbososcorp.ilocation.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.verbososcorp.ilocation.util.Project.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/order")
public class OrderController {

	@Autowired
	private OrderService service;
				
	@GetMapping("")
	public ResponseEntity<List<OrderDTO>> getAll(){			
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/currentDeliveryPersonDeliveryPerson")
	public ResponseEntity<List<OrderDTO>> getAllByDelivyPerson(){
		return ResponseEntity.ok(service.getAllByDeliveryPerson());
	}

	@GetMapping("/opens")
	public ResponseEntity<List<OrderDTO>> getAllbyOpenStatus(){
		return ResponseEntity.ok(service.getAllByOrderStatus());
	}

	@GetMapping("{id}")
	public ResponseEntity<OrderDTO> getOrderByID(@PathVariable Integer id){
		return ResponseEntity.ok(service.getOrderByID(id));
	}	

	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<OrderDTO>> getOrderByStatus(@PathVariable Integer status){
		return ResponseEntity.ok(service.getOrderByStatus(status));
	}

	
	@PatchMapping("/assign/{orderID}")
	public ResponseEntity<?> assignDeliveryPerson(@PathVariable Integer orderID){
		service.assignDeliveryPerson(orderID);
		return ResponseEntity.noContent().build();
	}
	
	
	@PatchMapping("/status/cancelled")
	public ResponseEntity<?> changeStatusToCancelled(){		
		service.changeStatusToCancelled();
		return ResponseEntity.noContent().build();
	}
	
	
	@PatchMapping("/status/delivered")
	public ResponseEntity<?> changeStatusToDelivered(){		
		service.changeStatusToDelivered();
		return ResponseEntity.noContent().build();
	}

}
