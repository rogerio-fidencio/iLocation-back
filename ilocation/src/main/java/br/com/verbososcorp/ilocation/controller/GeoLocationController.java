package br.com.verbososcorp.ilocation.controller;

import static br.com.verbososcorp.ilocation.util.Project.BASE_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.verbososcorp.ilocation.models.GeoLocation;
import br.com.verbososcorp.ilocation.services.Impl.GeoLocationServiceImpl;

@RestController
@RequestMapping(BASE_URL + "/geolocation")
public class GeoLocationController {
	
	@Autowired
	GeoLocationServiceImpl service;
	
	@PutMapping()
	public ResponseEntity<GeoLocation> registerGeoLocation(@RequestBody GeoLocation geoLocation) {
		return service.register(geoLocation);
	}
	
}
