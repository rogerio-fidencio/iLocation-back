package br.com.verbososcorp.ilocation.services.Impl;

import br.com.verbososcorp.ilocation.DAO.CustomerDAO;
import br.com.verbososcorp.ilocation.services.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDAO dao;


}
