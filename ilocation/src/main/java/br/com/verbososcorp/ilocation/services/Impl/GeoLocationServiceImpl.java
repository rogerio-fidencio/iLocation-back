package br.com.verbososcorp.ilocation.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.verbososcorp.ilocation.DAO.GeoLocationDAO;
import br.com.verbososcorp.ilocation.models.GeoLocation;
import br.com.verbososcorp.ilocation.services.interfaces.GeoLocationService;

@Component
@Primary
@Qualifier("default")
public class GeoLocationServiceImpl implements GeoLocationService {

	@Autowired
	private GeoLocationDAO dao;
	
	@Override
	public ResponseEntity<GeoLocation> register(GeoLocation newGeoLocation) {
		dao.save(newGeoLocation);
		return ResponseEntity.status(HttpStatus.CREATED).body(newGeoLocation);
	}
	
}
