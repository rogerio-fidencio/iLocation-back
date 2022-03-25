package br.com.verbososcorp.ilocation.DAO;

import br.com.verbososcorp.ilocation.DTO.GeoLocationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.verbososcorp.ilocation.models.GeoLocation;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GeoLocationDAO extends CrudRepository<GeoLocation, Integer> {

    @Query(  value =
                "SELECT new br.com.verbososcorp.ilocation.DTO.GeoLocationDTO " +
                "(geolocation.timestamp, geolocation.longitude, geolocation.latitude) " +
                "FROM GeoLocation AS geolocation " +
                "WHERE geolocation.order.id = :id",
            countQuery =
                    "SELECT count(*) " +
                    "FROM GeoLocation AS geolocation " +
                    "WHERE geolocation.order.id = :id",
            nativeQuery = false)

    public Page<GeoLocationDTO> getGeolocationPageByOrderID(@Param("id") Integer id, Pageable page);
}
