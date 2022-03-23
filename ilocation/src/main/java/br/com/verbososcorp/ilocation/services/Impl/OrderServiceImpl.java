package br.com.verbososcorp.ilocation.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.verbososcorp.ilocation.DAO.OrderDAO;
import br.com.verbososcorp.ilocation.exceptions.exceptions.ResourceNotFoundException;
import br.com.verbososcorp.ilocation.models.GeoLocation;
import br.com.verbososcorp.ilocation.models.Order;
import br.com.verbososcorp.ilocation.services.interfaces.OrderService;

@Component
@Primary
@Qualifier("default")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDAO dao;

	@Override
	public ResponseEntity<List<Order>> getAll() {
		List<Order> orderList = (List<Order>)dao.findAll();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderList);
	}

	@Override
	public ResponseEntity<Order> getByID(Integer id) {
		Optional<Order> order = dao.findById(id);
		
		if(order.isEmpty()) {
			throw new ResourceNotFoundException("Pedido não encontrado");
		}
					
		return ResponseEntity.ok(order.get());
	}

	@Override
	public ResponseEntity<List<GeoLocation>> getTracking(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Order>> getByStatus(Integer status) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
