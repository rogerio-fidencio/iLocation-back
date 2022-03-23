package br.com.verbososcorp.ilocation.services.Impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.verbososcorp.ilocation.services.interfaces.GeoLocationService;

@Component
@Primary
@Qualifier("default")
public class GeoLocationServiceImpl implements GeoLocationService {

}
