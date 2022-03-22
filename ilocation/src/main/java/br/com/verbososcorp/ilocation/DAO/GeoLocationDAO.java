package br.com.verbososcorp.ilocation.DAO;

import org.springframework.data.repository.CrudRepository;

import br.com.verbososcorp.ilocation.models.GeoLocation;

public interface GeoLocationDAO extends CrudRepository<GeoLocation, Integer> {

}
