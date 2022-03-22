package br.com.verbososcorp.ilocation.services.Impl;

import br.com.verbososcorp.ilocation.DAO.DeliveryPersonDAO;
import br.com.verbososcorp.ilocation.services.interfaces.DeliveryPersonService;
import org.springframework.beans.factory.annotation.Autowired;

public class DeliveryPersonServiceImpl implements DeliveryPersonService {

    @Autowired
    DeliveryPersonDAO deliveryPersonDAO;

}
