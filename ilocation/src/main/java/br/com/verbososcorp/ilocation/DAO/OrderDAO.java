package br.com.verbososcorp.ilocation.DAO;

import org.springframework.data.repository.CrudRepository;

import br.com.verbososcorp.ilocation.models.Order;

public interface OrderDAO extends CrudRepository<Order, Integer> {
	


}
