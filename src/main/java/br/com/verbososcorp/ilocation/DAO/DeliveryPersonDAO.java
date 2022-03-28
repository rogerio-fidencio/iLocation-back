package br.com.verbososcorp.ilocation.DAO;

import br.com.verbososcorp.ilocation.DTO.DeliveryPersonAuthDTO;
import br.com.verbososcorp.ilocation.DTO.DeliveryPersonDTO;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DeliveryPersonDAO extends CrudRepository<DeliveryPerson, Integer> {

    public Optional<DeliveryPerson> findByEmail(String email);


    @Query("SELECT new br.com.verbososcorp.ilocation.DTO.DeliveryPersonAuthDTO " +
            "(deliveryPerson.email, deliveryPerson.password) " +
            "FROM DeliveryPerson as deliveryPerson " +
            "WHERE deliveryPerson.email = :emailOrCPF " +
            "OR deliveryPerson.cpf = :emailOrCPF")
    public Optional<DeliveryPersonAuthDTO> findDeliveryPersonAuthDTOByEmailOrCPF(@Param("emailOrCPF") String emailOrCPF);

    @Query("SELECT new br.com.verbososcorp.ilocation.DTO.DeliveryPersonDTO " +
            "(deliveryPerson.id, deliveryPerson.name, deliveryPerson.phone) " +
            "FROM DeliveryPerson as deliveryPerson " +
            "WHERE deliveryPerson.email = :emailOrCPF " +
            "OR deliveryPerson.cpf = :emailOrCPF")
    public Optional<DeliveryPersonDTO> findDeliveryPersonDTOByEmailOrCPF(@Param("emailOrCPF") String emailOrCPF);
}
