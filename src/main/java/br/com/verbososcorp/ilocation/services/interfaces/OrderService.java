package br.com.verbososcorp.ilocation.services.interfaces;

import br.com.verbososcorp.ilocation.DTO.OrderDTO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.*;
import br.com.verbososcorp.ilocation.models.Order;

import java.util.List;


public interface OrderService {
	
	public List<OrderDTO> getAll();
	
	public OrderDTO getOrderByID(Integer id) throws OrderNotFoundException;

	public List<OrderDTO> getOrderByStatus(Integer status) throws InvalidStatusException;
	
	public Order assignDeliveryPerson(Integer orderID) throws DeliveryPersonNotAvailableException, OrderNotFoundException, OrderNotAvailableException;	
	
	public Order changeStatusToCancelled() throws NoOrderAtributedToDeliveryPersonException, OrderCannotBeCancelledException;
	
	public Order changeStatusToDelivered() throws NoOrderAtributedToDeliveryPersonException, OrderCannotBeConcludedException;

	public OrderDTO getCurrentOrderByDeliveryPerson() throws DeliveryPersonNotFoundException;

	public List<OrderDTO> getAllByDeliveryPerson();

	public List<OrderDTO> getAllbyAvailableStatus();
}
