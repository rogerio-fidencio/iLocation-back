package br.com.verbososcorp.ilocation.services.interfaces;

import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import org.springframework.http.ResponseEntity;

public interface DeliveryPersonService {
    ResponseEntity<DeliveryPerson> register(DeliveryPerson newDeliveryPerson);

    ResponseEntity<DeliveryPerson> FindByEmail(String email);
}
