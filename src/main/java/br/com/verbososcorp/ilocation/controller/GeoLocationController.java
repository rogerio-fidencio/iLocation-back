package br.com.verbososcorp.ilocation.controller;

import br.com.verbososcorp.ilocation.DTO.GeoLocationDTO;
import br.com.verbososcorp.ilocation.models.GeoLocation;
import br.com.verbososcorp.ilocation.services.interfaces.GeoLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.verbososcorp.ilocation.util.Project.BASE_URL;


@RestController
@RequestMapping(BASE_URL + "/geolocation")
public class GeoLocationController {
	
	@Autowired
	GeoLocationService service;
	
	@PutMapping()
	public ResponseEntity<?> registerGeoLocation(@RequestBody GeoLocation geoLocation)  {
		Object res = service.register(geoLocation);
		
		if (res == null) {
			return ResponseEntity.status(500).build();
			//TODO verificar que erro colocar aqui. Exception?
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{orderID}")
	public ResponseEntity<Page<GeoLocationDTO>> getAllGeoLocationsByOrderID(@PathVariable Integer orderID, Pageable pageable){
		Page<GeoLocationDTO> geoLocationDTOList = (Page<GeoLocationDTO>) service.getGeoLocationPageByOrderID(orderID, pageable);

		return ResponseEntity.ok(geoLocationDTOList);
	}
	
}
