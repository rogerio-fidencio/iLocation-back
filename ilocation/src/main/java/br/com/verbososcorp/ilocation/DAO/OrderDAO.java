package br.com.verbososcorp.ilocation.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.verbososcorp.ilocation.DTO.OrderDTO;
import br.com.verbososcorp.ilocation.models.Order;

public interface OrderDAO extends CrudRepository<Order, Integer> {
	
	@Query("select new br.com.verbososcorp.ilocation.DTO.OrderDTO(order.id, order.status, order.deliveryPerson.id) FROM Order as order WHERE order.status = 1 AND order.deliveryPerson.id = :id")
	public Optional<OrderDTO> getCurrentOrderByDeliveryPersonId(@Param("id") Integer id);
	
	
	public List<Order> getByStatus(Integer status);

}
