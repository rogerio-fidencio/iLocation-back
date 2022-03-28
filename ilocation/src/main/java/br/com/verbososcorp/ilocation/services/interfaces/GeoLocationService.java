package br.com.verbososcorp.ilocation.services.interfaces;

import br.com.verbososcorp.ilocation.DTO.GeoLocationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.verbososcorp.ilocation.models.GeoLocation;

public interface GeoLocationService {
	GeoLocation register(GeoLocation newGeoLocation);

	Page<GeoLocationDTO> getGeoLocationPageByOrderID(Integer orderID, Pageable pageable);
}
