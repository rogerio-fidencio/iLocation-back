package br.com.verbososcorp.ilocation.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.verbososcorp.ilocation.DTO.DeliveryPersonDTO;
import br.com.verbososcorp.ilocation.DTO.OrderChangeStatusFormDTO;
import br.com.verbososcorp.ilocation.DTO.OrderDTO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.BadRequestException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import br.com.verbososcorp.ilocation.DAO.DeliveryPersonDAO;
import br.com.verbososcorp.ilocation.DAO.OrderDAO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.ResourceNotFoundException;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import br.com.verbososcorp.ilocation.models.GeoLocation;
import br.com.verbososcorp.ilocation.models.Order;
import br.com.verbososcorp.ilocation.services.interfaces.OrderService;
import br.com.verbososcorp.ilocation.util.Project;

@Component
@Primary
@Qualifier("default")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO dao;
    
    @Autowired
	private DeliveryPersonDAO deliveryPersonDAO;

    @Override
    public List<Order> getAll() {
    	
        try {
			List<Order> orderList = (List<Order>) dao.findAll();
			return orderList;
			
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}
        
    }
    

    @Override
    public Order getByID(Integer id) {
        try {
			Optional<Order> order = dao.findById(id);

			if (order.isEmpty()) {
			    throw new ResourceNotFoundException("Pedido não encontrado");
			}

			return order.get();
			
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}
    }

    
    @Override
    public List<GeoLocation> getTracking(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }
    

    @Override
    public List<Order> getByStatus(Integer status) {
        
    	try {
    		List<Order> completeOrderList = (List<Order>) dao.findAll();
        	
        	if (status >3 || status < 0) {
        		throw new BadRequestException("Status inválido.");
        	}
        	
        	List<Order> orderListByStatus = new ArrayList<Order>();
        	
        	for (Order o: completeOrderList) {
        		
        		if(o.getStatus() == status) {    			
        			orderListByStatus.add(o);
        		} 		
        		
        	}    	        	
         	
            return orderListByStatus;
            
    	}catch(Exception e) {
    		
    		throw new InternalServerErrorException(e.getMessage());
    		
    	}    	    	
    }

    @Override

    //0 - Em Aberto: Pedidos aguardando para serem escolhidos pela pessoa entregadora para entrega
    //1 - A Caminho: Após pessoa entregadora pegar o pedido para entrega. Pedido está em rota de entrega
    //2 - Entregue: Pedido entregue ao cliente
    //3 - Cancelado

    public Order changeStatus(OrderChangeStatusFormDTO orderChangeStatusForm) {
        try {
            Optional<Order> orderToChangeOptional = dao.findById(orderChangeStatusForm.getOrderID());

            if (orderToChangeOptional.isEmpty()) {
                throw new ResourceNotFoundException("Pedido não encontrado.");
            }

            Order orderToChange = orderToChangeOptional.get();

            if (orderToChange.getStatus() == 0) {
                if (orderChangeStatusForm.getStatus() > 1) {
                    throw new BadRequestException("Status inválido, pedido aguardado entregador.");
                }
            }

            if (orderToChange.getStatus() == 1) {
                if (orderChangeStatusForm.getStatus() < 1) {
                    throw new BadRequestException("Status inválido, pedido já está em transporte.");
                }
            }
            if (orderToChange.getStatus() == 2) {
                throw new BadRequestException("Status inválido, Pedido já entregue.");
            }

            orderToChange.setStatus(orderChangeStatusForm.getStatus());
            dao.save(orderToChange);
            return orderToChange;

        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

	@Override
	public Order assignDeliveryPerson(Integer orderID) {			
		
		try {
			DeliveryPersonDTO user = Project.getContextData();
			
			Integer userID = user.getId();
			
			Optional<OrderDTO> orderValidation = dao.getCurrentOrderByDeliveryPersonId(userID);
			
			if(orderValidation.isPresent()) {
				throw new BadRequestException("Entregador não disponível para atribuição");
			}			
		
			if (orderID == null || userID == null) {
				throw new BadRequestException("Número do pedido e pessoa entregadora devem ser informados");
			}		
			
			Optional<Order> order = dao.findById(orderID);
			
			if(order.isEmpty()) {
				throw new ResourceNotFoundException("Pedido não encontrado");
			}
			
			if (order.get().getStatus() != 0) {
				throw new BadRequestException("Pedido não disponível para atribuição");
			}					
			
			Optional<DeliveryPerson> deliveryPerson = deliveryPersonDAO.findById(userID);		
					
			if(deliveryPerson.isEmpty()) {
				throw new ResourceNotFoundException("Pessoa Entregadora não encontrada"); 
			}		
			
			order.get().setDeliveryPerson(deliveryPerson.get());			
			order.get().setStatus(1);				
			dao.save(order.get());
				
			return order.get();
			
		}catch(Exception e) {
			
			throw new InternalServerErrorException(e.getMessage());					
			
		}
	}


}
