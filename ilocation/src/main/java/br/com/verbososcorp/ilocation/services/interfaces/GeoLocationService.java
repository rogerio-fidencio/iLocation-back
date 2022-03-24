package br.com.verbososcorp.ilocation.services.interfaces;

import org.springframework.http.ResponseEntity;

import br.com.verbososcorp.ilocation.models.GeoLocation;

public interface GeoLocationService {
	ResponseEntity<GeoLocation> register(GeoLocation newGeoLocation);
}
