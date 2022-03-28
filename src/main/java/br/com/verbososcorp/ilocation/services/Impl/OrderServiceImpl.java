package br.com.verbososcorp.ilocation.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.verbososcorp.ilocation.DAO.DeliveryPersonDAO;
import br.com.verbososcorp.ilocation.DAO.OrderDAO;
import br.com.verbososcorp.ilocation.DTO.OrderDTO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.BadRequestException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.InternalServerErrorException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.ResourceNotFoundException;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;
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
    public List<OrderDTO> getAll() {
    	
        try {
			List<OrderDTO> orderList = dao.getAllOrders();
			
			return orderList;
			
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}
        
    }
    
    
    @Override
    public OrderDTO getOrderByID(Integer id) {
        try {
			Optional<OrderDTO> order = dao.getOrderById(id);

			if (order.isEmpty()) {
			    throw new ResourceNotFoundException("Pedido não encontrado.");
			}

			return order.get();
			
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
			
		} catch (Exception e) {
			throw new InternalServerErrorException(e.getMessage());
		}
    }


	@Override
	public List<OrderDTO> getOrderByStatus(Integer status) {
		
		try {
	       	if (status >3 || status < 0) {
        		throw new BadRequestException("Status inválido.");
        	}
	       	
	       	List<OrderDTO> orderListByStatus = dao.getOrderByStatus(status);	    
			
	       	return orderListByStatus;
	       	
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
			
    	}catch(Exception e) {    		
    		throw new InternalServerErrorException(e.getMessage());
    		
    	}    		
		
	}


    //0 - Em Aberto: Pedidos aguardando para serem escolhidos pela pessoa entregadora para entrega
    //1 - A Caminho: Após pessoa entregadora pegar o pedido para entrega. Pedido está em rota de entrega
    //2 - Entregue: Pedido entregue ao cliente
    //3 - Cancelado

   
	@Override
	public void assignDeliveryPerson(Integer orderID) {			
		
		try {			
			
			Integer userID = Project.getContextData().getId();
			
			Optional<OrderDTO> orderValidation = dao.getCurrentOrderByDeliveryPersonId(userID);
			
			if(orderValidation.isPresent()) {
				throw new BadRequestException("Entregador não disponível para atribuição.");
			}				
			
			Optional<Order> order = dao.findById(orderID);
			
			if(order.isEmpty()) {
				throw new ResourceNotFoundException("Pedido não encontrado.");
			}
			
			if (order.get().getStatus() != 0) {
				throw new BadRequestException("Pedido não disponível para atribuição.");
			}					
			
			Optional<DeliveryPerson> deliveryPerson = deliveryPersonDAO.findById(userID);		
				
			order.get().setDeliveryPerson(deliveryPerson.get());			
			order.get().setStatus(1);				
			dao.save(order.get());
				
			return;
		
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
			
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
			
		}catch(Exception e) {			
			throw new InternalServerErrorException(e.getMessage());					
			
		}
	}


	@Override
	public void changeStatusToCancelled() {
		
		try {
			Integer userID = Project.getContextData().getId();
			
			Optional<OrderDTO> currentOrder = dao.getCurrentOrderByDeliveryPersonId(userID);
			
			if(currentOrder.isEmpty()) {
				throw new BadRequestException("Nenhum pedido está atribuído a este entregador.");
			}
			
			if(currentOrder.get().getStatus() != 1) {
				throw new BadRequestException("Pedido não pode ser cancelado.");
			}
			
			Optional<Order> order = dao.findById(currentOrder.get().getId());
			
			order.get().setStatus(3);
			dao.save(order.get());
			
			return;
			
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
			
		} catch (Exception e) {			
			throw new InternalServerErrorException(e.getMessage());		
		}	
		
	}


	@Override
	public void changeStatusToDelivered() {
		
		try {
			Integer userID = Project.getContextData().getId();
			
			Optional<OrderDTO> currentOrder = dao.getCurrentOrderByDeliveryPersonId(userID);
			
			if(currentOrder.isEmpty()) {
				throw new BadRequestException("Nenhum pedido está atribuído a este entregador.");
			}
			
			if(currentOrder.get().getStatus() != 1) {
				throw new BadRequestException("Status do pedido não pode ser alterado para 'Entregue'.");
			}
			
			Optional<Order> order = dao.findById(currentOrder.get().getId());
			
			order.get().setStatus(2);
			dao.save(order.get());
			
			return;
		
		} catch (BadRequestException e) {
			throw new BadRequestException(e.getMessage());
			
		} catch (Exception e) {			
			throw new InternalServerErrorException(e.getMessage());		
		}
		
	}

}
