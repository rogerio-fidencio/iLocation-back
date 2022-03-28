package br.com.verbososcorp.ilocation.services.Impl;

import br.com.verbososcorp.ilocation.DAO.CustomerDAO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.InternalServerErrorException;
import br.com.verbososcorp.ilocation.models.Customer;
import br.com.verbososcorp.ilocation.services.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
@Qualifier("default")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDAO dao;


    @Override
    public List<Customer> getall() {
    	
        try {
			List<Customer> customerList = (List<Customer>) dao.findAll();
			return customerList;
			
		} catch (Exception e) {
			
			throw new InternalServerErrorException(e.getMessage());
		}
    }
}

