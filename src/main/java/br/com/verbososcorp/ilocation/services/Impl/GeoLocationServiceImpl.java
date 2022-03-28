package br.com.verbososcorp.ilocation.services.Impl;

import br.com.verbososcorp.ilocation.DAO.GeoLocationDAO;
import br.com.verbososcorp.ilocation.DAO.OrderDAO;
import br.com.verbososcorp.ilocation.DTO.GeoLocationDTO;
import br.com.verbososcorp.ilocation.DTO.OrderDTO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.BadRequestException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.InternalServerErrorException;
import br.com.verbososcorp.ilocation.models.GeoLocation;
import br.com.verbososcorp.ilocation.models.Order;
import br.com.verbososcorp.ilocation.services.interfaces.GeoLocationService;
import br.com.verbososcorp.ilocation.util.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@Primary
@Qualifier("default")
public class GeoLocationServiceImpl implements GeoLocationService {

    @Autowired
    private GeoLocationDAO dao;
    
    @Autowired
    private OrderDAO orderDAO;

    @Override
    public GeoLocation register(GeoLocation newGeoLocation) {
 		//TODO validação do tipo de dado que está recebendo do corpo da requisição
        
    	try {
            Integer userID = Project.getContextData().getId();
    		
    		Optional<OrderDTO> currentOptionalOrderDTO = orderDAO.getCurrentOrderByDeliveryPersonId(userID);
    		
    		if(currentOptionalOrderDTO.isEmpty()) {
    			throw new BadRequestException("A pessoa entregadora não possui pedido em andamento.");        
    		}
    		
    		
    		OrderDTO currentOrderDTO = currentOptionalOrderDTO.get();
    		
    		Order currentOrder = (Order)orderDAO.findById(currentOrderDTO.getId()).get();
    		
    		newGeoLocation.setOrder(currentOrder);        	 
            
            return dao.save(newGeoLocation);
            
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
