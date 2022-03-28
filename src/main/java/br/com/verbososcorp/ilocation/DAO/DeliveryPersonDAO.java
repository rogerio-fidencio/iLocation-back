package br.com.verbososcorp.ilocation.DAO;

import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeliveryPersonDAO extends CrudRepository<DeliveryPerson, Integer> {

    public Optional<DeliveryPerson> findByEmail(String email);
}
