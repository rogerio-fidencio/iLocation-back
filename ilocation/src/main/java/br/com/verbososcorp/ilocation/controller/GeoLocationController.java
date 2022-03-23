package br.com.verbososcorp.ilocation.controller;

import static br.com.verbososcorp.ilocation.util.Project.BASE_URL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.verbososcorp.ilocation.DAO.GeoLocationDAO;
import br.com.verbososcorp.ilocation.models.GeoLocation;

@RestController
@RequestMapping(BASE_URL + "/geolocation")
public class GeoLocationController {
	
}
