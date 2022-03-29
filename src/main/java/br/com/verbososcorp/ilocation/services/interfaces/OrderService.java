package br.com.verbososcorp.ilocation.services.interfaces;

import br.com.verbososcorp.ilocation.DTO.OrderDTO;

import java.util.List;


public interface OrderService {
	
	public List<OrderDTO> getAll();
	
	public OrderDTO getOrderByID(Integer id);

	public List<OrderDTO> getOrderByStatus(Integer status);
	
	public void assignDeliveryPerson(Integer orderID);	
	
	public void changeStatusToCancelled();
	
	public void changeStatusToDelivered();

	public List<OrderDTO> getAllByDeliveryPerson();

	public List<OrderDTO> getAllByOrderStatus();
}
