package br.com.verbososcorp.ilocation.services.interfaces;

import br.com.verbososcorp.ilocation.DTO.OrderDTO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.DeliveryPersonNotAvailableException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.InvalidStatusException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.NoOrderAtributedToDeliveryPersonException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.OrderCannotBeCancelledException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.OrderCannotBeConcludedException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.OrderNotAvailableException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.OrderNotFoundException;
import br.com.verbososcorp.ilocation.models.Order;

import java.util.List;


public interface OrderService {
	
	public List<OrderDTO> getAll();
	
	public OrderDTO getOrderByID(Integer id) throws OrderNotFoundException;

	public List<OrderDTO> getOrderByStatus(Integer status) throws InvalidStatusException;
	
	public Order assignDeliveryPerson(Integer orderID) throws DeliveryPersonNotAvailableException, OrderNotFoundException, OrderNotAvailableException;	
	
	public Order changeStatusToCancelled() throws NoOrderAtributedToDeliveryPersonException, OrderCannotBeCancelledException;
	
	public Order changeStatusToDelivered() throws NoOrderAtributedToDeliveryPersonException, OrderCannotBeConcludedException;

	public List<OrderDTO> getAllByDeliveryPerson();

	public List<OrderDTO> getAllbyAvailableStatus();
}
