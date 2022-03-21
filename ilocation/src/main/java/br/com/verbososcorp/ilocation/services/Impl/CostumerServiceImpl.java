package br.com.verbososcorp.ilocation.services.Impl;

import br.com.verbososcorp.ilocation.DAO.CostumerDAO;
import br.com.verbososcorp.ilocation.services.interfaces.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;

public class CostumerServiceImpl implements CostumerService {

    @Autowired
    CostumerDAO dao;


}
