package br.com.verbososcorp.ilocation.services.interfaces;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.verbososcorp.ilocation.exceptions.customExceptions.DeliveryPersonNotAvailableException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.InvalidStatusException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.OrderNotFoundException;

@SpringBootTest
class OrderServiceTest {
	
	@Autowired
	private OrderService service;

	@Test
	public void shoudReturnGetAll() {
		Assertions.assertTrue(service.getAll().size() >= 0);
		
	}
	
	@Test
	public void shoudExistGetAll() {
		Assertions.assertTrue(service.getAll() != null);
	}
	
	@Test
	public void shouldReturnOrderNotFoundException() throws Exception {
		try {
			service.getOrderByID(-1);
			fail();
		} catch(OrderNotFoundException e) {
			Assertions.assertTrue(true);
		}		
	}
	
	
	@Test
	public void shouldReturnInvalidOrderStatus() throws Exception {
		try {
			service.getOrderByStatus(4);
			fail();
		} catch(InvalidStatusException e) {
			Assertions.assertTrue(true);
		}		
	}
	


	
}
