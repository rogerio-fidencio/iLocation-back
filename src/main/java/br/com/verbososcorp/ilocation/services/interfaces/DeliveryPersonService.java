package br.com.verbososcorp.ilocation.services.interfaces;

import br.com.verbososcorp.ilocation.DTO.DeliveryPersonDTO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.DeliveryPersonNotFoundException;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;

import java.util.List;

public interface DeliveryPersonService {
    DeliveryPerson register(DeliveryPerson newDeliveryPerson);

    DeliveryPerson getByEmail(String email) throws DeliveryPersonNotFoundException;

    List<DeliveryPerson> getAll();

    DeliveryPersonDTO findDeliveryPersonDTOByEmailOrPhone(String emailOrPhone) throws DeliveryPersonNotFoundException;
}
