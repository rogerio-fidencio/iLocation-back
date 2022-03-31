package br.com.verbososcorp.ilocation.controller;

import static br.com.verbososcorp.ilocation.util.Project.BASE_URL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.verbososcorp.ilocation.DTO.OrderDTO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.BadRequestException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.DeliveryPersonNotAvailableException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.InternalServerErrorException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.InvalidStatusException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.NoOrderAtributedToDeliveryPersonException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.OrderCannotBeCancelledException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.OrderCannotBeConcludedException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.OrderNotAvailableException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.OrderNotFoundException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.ResourceNotFoundException;
import br.com.verbososcorp.ilocation.services.interfaces.OrderService;

@RestController
@CrossOrigin("*")
@RequestMapping(BASE_URL + "/order")
public class OrderController {

	@Autowired
	private OrderService service;
				
	@GetMapping("")
	public ResponseEntity<List<OrderDTO>> getAll(){
		
		try {
			return ResponseEntity.ok(service.getAll());
			
		}catch(Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}	
	}
	

	@GetMapping("{id}")
	public ResponseEntity<OrderDTO> getOrderByID(@PathVariable Integer id) {
		
		try {			
			return ResponseEntity.ok(service.getOrderByID(id));	
			
		} catch (OrderNotFoundException e) {			
			throw new BadRequestException(e.getMessage());
			
		}catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}
		
	}	

	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<OrderDTO>> getOrderByStatus(@PathVariable Integer status) {		
		
		try {
			return ResponseEntity.ok(service.getOrderByStatus(status));
			
		} catch (InvalidStatusException e) {			
			throw new BadRequestException(e.getMessage());
			
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}
	}

	
	@PatchMapping("/assign/{orderID}")
	public ResponseEntity<?> assignDeliveryPerson(@Validated @PathVariable Integer orderID) {
		try {
			service.assignDeliveryPerson(orderID);
			
		} catch (DeliveryPersonNotAvailableException | OrderNotAvailableException e) {			
			throw new BadRequestException(e.getMessage());
			
		} catch (OrderNotFoundException e) {			
			throw new ResourceNotFoundException(e.getMessage());
			
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}
		
		return ResponseEntity.noContent().build();
	}
	
	
	@PatchMapping("/status/cancelled")
	public ResponseEntity<?> changeStatusToCancelled(){		
		try {
			service.changeStatusToCancelled();
			
		} catch (NoOrderAtributedToDeliveryPersonException | OrderCannotBeCancelledException e) {
			throw new BadRequestException(e.getMessage());
				
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
			
		}		
		return ResponseEntity.noContent().build();
	}
	
	
	@PatchMapping("/status/delivered")
	public ResponseEntity<?> changeStatusToDelivered() {		
		try {
			service.changeStatusToDelivered();
			
		} catch (NoOrderAtributedToDeliveryPersonException | OrderCannotBeConcludedException e) {
			throw new BadRequestException(e.getMessage());		
	
		}catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
			
		}
		return ResponseEntity.noContent().build();
	}

	
	@GetMapping("/available")
	public ResponseEntity<List<OrderDTO>> getAllbyAvailableStatus(){
		
		try {
			return ResponseEntity.ok(service.getAllbyAvailableStatus());
			
        }catch (Exception e){
            throw new InternalServerErrorException(e.getMessage());
            
        }		
		
	}
	
	
	@GetMapping("/currentDeliveryPerson")
	public ResponseEntity<List<OrderDTO>> getAllByDeliveryPerson(){
		
		try {
			return ResponseEntity.ok(service.getAllByDeliveryPerson());
			
		} catch (Exception e) {			
			throw new InternalServerErrorException(e.getMessage());
		}
	}
	
		
}
