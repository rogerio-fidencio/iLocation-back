package br.com.verbososcorp.ilocation.services.Impl;

import br.com.verbososcorp.ilocation.DTO.GeoLocationDTO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.BadRequestException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.verbososcorp.ilocation.DAO.GeoLocationDAO;
import br.com.verbososcorp.ilocation.models.GeoLocation;
import br.com.verbososcorp.ilocation.services.interfaces.GeoLocationService;

import java.util.Optional;
import java.util.stream.Stream;

@Component
@Primary
@Qualifier("default")
public class GeoLocationServiceImpl implements GeoLocationService {

    @Autowired
    private GeoLocationDAO dao;

    @Override
    public GeoLocation register(GeoLocation newGeoLocation) {
        try {
            dao.save(newGeoLocation);
            return newGeoLocation;
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public Page<GeoLocationDTO> getGeoLocationPageByOrderID(Integer orderID, Pageable pageable) {
        try {
            Page<GeoLocationDTO> geoLocationDTOList = dao.getGeolocationPageByOrderID(orderID, pageable);

            if (geoLocationDTOList.isEmpty()) {
                throw new BadRequestException("Lista de geolocalização vazia.");
            };
            return geoLocationDTOList;

        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

}
