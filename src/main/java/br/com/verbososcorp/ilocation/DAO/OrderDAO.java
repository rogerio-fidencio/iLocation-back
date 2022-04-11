package br.com.verbososcorp.ilocation.DAO;

import br.com.verbososcorp.ilocation.DTO.OrderDTO;
import br.com.verbososcorp.ilocation.models.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderDAO extends CrudRepository<Order, Integer> {

	@Query("select new br.com.verbososcorp.ilocation.DTO.OrderDTO" +
			"(order.id, order.status, order.deliveryPerson.id)" +
			" FROM Order as order " +
			"WHERE order.deliveryPerson.id = :id")
	List<OrderDTO> getAllByDeliveryPersonId(@Param("id") Integer deliveryPersonID);


	@Query("select new br.com.verbososcorp.ilocation.DTO.OrderDTO" +
			"(order.id, order.status, order.deliveryPerson.id, " +
			"order.date, order.customer.id, order.customer.name, order.customer.cep, " +
			"order.customer.numRes, order.customer.compl) " +
			"FROM Order as order " +
			"WHERE order.status = 1 " +
			"AND order.deliveryPerson.id = :id")
	Optional<OrderDTO> getCurrentOrderByDeliveryPersonId(@Param("id") Integer id);


	@Query("select new br.com.verbososcorp.ilocation.DTO.OrderDTO" +
			"(order.id, order.status, order.deliveryPerson.id, order.date, customer.id, " +
			"customer.name, customer.cep, customer.numRes, customer.compl) " +
			"FROM " +
			"Order as order INNER JOIN Customer as customer ON " +
			"order.customer = customer.id")
	List<OrderDTO> getAllOrders();


	@Query("select new br.com.verbososcorp.ilocation.DTO.OrderDTO" +
			"(order.id, order.status, order.deliveryPerson.id, order.date, customer.id, " +
			"customer.name, customer.cep, customer.numRes, customer.compl) " +
			"FROM " +
			"Order as order " +
			"INNER JOIN Customer as customer ON " +
			"order.customer = customer.id " +
			"WHERE order.id = :id")
	Optional<OrderDTO> getOrderById(@Param("id") Integer id);


	@Query("select new br.com.verbososcorp.ilocation.DTO.OrderDTO" +
			"(order.id, order.status, order.deliveryPerson.id, order.date, customer.id, " +
			"customer.name, customer.cep, customer.numRes, customer.compl) " +
			"FROM " +
			"Order as order " +
			"INNER JOIN Customer as customer ON " +
			"order.customer = customer.id " +
			"WHERE order.status = :status")
	List<OrderDTO> getOrderByStatus(@Param("status") Integer status);

}
