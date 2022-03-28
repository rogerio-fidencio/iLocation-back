package br.com.verbososcorp.ilocation.services.interfaces;

import br.com.verbososcorp.ilocation.DTO.DeliveryPersonDTO;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;

import java.util.List;

public interface DeliveryPersonService {
    DeliveryPerson register(DeliveryPerson newDeliveryPerson);

    DeliveryPerson getByEmail(String email);

    List<DeliveryPerson> getAll();

    DeliveryPersonDTO findDeliveryPersonDTOByEmailOrCPF(String email);
}
