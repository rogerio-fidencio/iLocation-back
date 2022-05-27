package br.com.verbososcorp.ilocation.services.interfaces;

import br.com.verbososcorp.ilocation.DTO.OrderDTO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.*;
import br.com.verbososcorp.ilocation.models.Order;

import java.util.List;


public interface OrderService {
	
	List<OrderDTO> getAll();

	OrderDTO getOrderByID(Integer id) throws OrderNotFoundException;

	List<OrderDTO> getOrderByStatus(Integer status) throws InvalidStatusException;

	Order assignDeliveryPerson(Integer orderID) throws DeliveryPersonNotAvailableException, OrderNotFoundException, OrderNotAvailableException;

	Order changeStatusToCancelled() throws NoOrderAtributedToDeliveryPersonException, OrderCannotBeCancelledException;

	Order changeStatusToDelivered() throws NoOrderAtributedToDeliveryPersonException, OrderCannotBeConcludedException;

	OrderDTO getCurrentOrderByDeliveryPerson() throws DeliveryPersonNotFoundException;

	List<OrderDTO> getAllByDeliveryPerson();

	List<OrderDTO> getAllbyAvailableStatus();
}
