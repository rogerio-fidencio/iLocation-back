package br.com.verbososcorp.ilocation.DAO;

import br.com.verbososcorp.ilocation.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDAO extends CrudRepository<Customer, Integer> {

}
