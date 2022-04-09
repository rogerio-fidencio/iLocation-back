package br.com.verbososcorp.ilocation.controller;

import br.com.verbososcorp.ilocation.DTO.GeoLocationDTO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.BadRequestException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.InternalServerErrorException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.NoOrderAtributedToDeliveryPersonException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.OrderNotFoundException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.ResourceNotFoundException;
import br.com.verbososcorp.ilocation.models.GeoLocation;
import br.com.verbososcorp.ilocation.services.interfaces.GeoLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static br.com.verbososcorp.ilocation.util.Project.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/geolocation")
public class GeoLocationController {

    @Autowired
    GeoLocationService service;

    @PutMapping()
    public ResponseEntity<?> registerGeoLocation(@Validated @RequestBody GeoLocation geoLocation) {

        try {
            service.register(geoLocation);
        } catch (NoOrderAtributedToDeliveryPersonException e) {
            throw new BadRequestException(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/{orderID}")
    public ResponseEntity<Page<GeoLocationDTO>> getAllGeoLocationsByOrderID(@PathVariable Integer orderID, Pageable pageable) {

        try {
            Page<GeoLocationDTO> geoLocationDTOList = (Page<GeoLocationDTO>) service.getGeoLocationPageByOrderID(orderID, pageable);
            return ResponseEntity.ok(geoLocationDTOList);

        } catch (OrderNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());

        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
