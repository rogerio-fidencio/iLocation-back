package br.com.verbososcorp.ilocation.services.interfaces;

import java.util.List;

import br.com.verbososcorp.ilocation.DTO.OrderChangeStatusFormDTO;
import br.com.verbososcorp.ilocation.models.GeoLocation;
import br.com.verbososcorp.ilocation.models.Order;

public interface OrderService {
	
	public List<Order> getAll();
	
	public Order getByID(Integer id);
	
	public List<GeoLocation> getTracking(Integer id);
	
	public List<Order> getByStatus(Integer status);

	public Order changeStatus(OrderChangeStatusFormDTO changeStatusForm);
	
	public Order assignDeliveryPerson(Integer orderID);
	
}
