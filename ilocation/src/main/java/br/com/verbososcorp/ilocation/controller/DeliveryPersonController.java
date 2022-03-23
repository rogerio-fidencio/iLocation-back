package br.com.verbososcorp.ilocation.controller;

import static br.com.verbososcorp.ilocation.util.Project.BASE_URL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.verbososcorp.ilocation.DAO.DeliveryPersonDAO;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;

@RestController
@RequestMapping(BASE_URL + "/deliveryperson")
public class DeliveryPersonController {
	
	@Autowired
	private DeliveryPersonDAO dao;
	
	@GetMapping("/getall")
	public List<DeliveryPerson> getall() {
		return (List<DeliveryPerson>)dao.findAll();
	}
	
}
