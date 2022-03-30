package br.com.verbososcorp.ilocation.services.Impl;

import static br.com.verbososcorp.ilocation.util.Project.getContextData;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import br.com.verbososcorp.ilocation.DAO.DeliveryPersonDAO;
import br.com.verbososcorp.ilocation.DAO.OrderDAO;
import br.com.verbososcorp.ilocation.DTO.OrderDTO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.DeliveryPersonNotAvailableException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.InvalidStatusException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.NoOrderAtributedToDeliveryPersonException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.OrderCannotBeCancelledException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.OrderCannotBeConcludedException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.OrderNotAvailableException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.OrderNotFoundException;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import br.com.verbososcorp.ilocation.models.Order;
import br.com.verbososcorp.ilocation.services.interfaces.OrderService;

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
            List<OrderDTO> orderList = dao.getAllOrders();
            return orderList;
    }


    @Override
    public OrderDTO getOrderByID(Integer id) throws OrderNotFoundException  {
    	
        Optional<OrderDTO> order = dao.getOrderById(id);

        if (order.isEmpty()) {
            throw new OrderNotFoundException();
        }

        return order.get();

    }   


    @Override
    public List<OrderDTO> getOrderByStatus(Integer status) throws InvalidStatusException {
       
        if (status > 3 || status < 0) {
            throw new InvalidStatusException();
        }

        List<OrderDTO> orderListByStatus = dao.getOrderByStatus(status);

        return orderListByStatus; 

    }


    //0 - Em Aberto: Pedidos aguardando para serem escolhidos pela pessoa entregadora para entrega
    //1 - A Caminho: Após pessoa entregadora pegar o pedido para entrega. Pedido está em rota de entrega
    //2 - Entregue: Pedido entregue ao cliente
    //3 - Cancelado


    @Override
    public Order assignDeliveryPerson(Integer orderID) throws DeliveryPersonNotAvailableException, OrderNotFoundException, OrderNotAvailableException {
 
        Integer userID = getContextData().getId();

        Optional<OrderDTO> orderValidation = dao.getCurrentOrderByDeliveryPersonId(userID);

        if (orderValidation.isPresent()) {
            throw new DeliveryPersonNotAvailableException();
        }

        Optional<Order> order = dao.findById(orderID);

        if (order.isEmpty()) {
            throw new OrderNotFoundException();
        }

        if (order.get().getStatus() != 0) {
            throw new OrderNotAvailableException();
        }

        Optional<DeliveryPerson> deliveryPerson = deliveryPersonDAO.findById(userID);

        order.get().setDeliveryPerson(deliveryPerson.get());
        order.get().setStatus(1);        

        return dao.save(order.get());

    }


    @Override
    public Order changeStatusToCancelled() throws NoOrderAtributedToDeliveryPersonException, OrderCannotBeCancelledException {
    
        Integer userID = getContextData().getId();

        Optional<OrderDTO> currentOrder = dao.getCurrentOrderByDeliveryPersonId(userID);

        if (currentOrder.isEmpty()) {
            throw new NoOrderAtributedToDeliveryPersonException();
        }

        if (currentOrder.get().getStatus() != 1) {
            throw new OrderCannotBeCancelledException();
        }

        Optional<Order> order = dao.findById(currentOrder.get().getId());

        order.get().setStatus(3);        

        return dao.save(order.get());
    }


    @Override
    public Order changeStatusToDelivered() throws NoOrderAtributedToDeliveryPersonException, OrderCannotBeConcludedException {
  
        Integer userID = getContextData().getId();

        Optional<OrderDTO> currentOrder = dao.getCurrentOrderByDeliveryPersonId(userID);

        if (currentOrder.isEmpty()) {
            throw new NoOrderAtributedToDeliveryPersonException();
        }

        if (currentOrder.get().getStatus() != 1) {
            throw new OrderCannotBeConcludedException();
        }

        Optional<Order> order = dao.findById(currentOrder.get().getId());
        order.get().setStatus(2);            
        return dao.save(order.get());

    }
    
    
    @Override
    public List<OrderDTO> getAllbyAvailableStatus() {  
        return dao.getAllByStatus(1);
    }

    
    @Override
    public List<OrderDTO> getAllByDeliveryPerson() { 
        Integer userID = getContextData().getId();
        return dao.getAllByDeliveryPersonId(userID);
    }  


}
