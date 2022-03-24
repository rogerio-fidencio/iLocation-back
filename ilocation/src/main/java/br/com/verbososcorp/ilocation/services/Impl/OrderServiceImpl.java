package br.com.verbososcorp.ilocation.services.Impl;

import java.util.List;
import java.util.Optional;

import br.com.verbososcorp.ilocation.DTO.OrderChangeStatusFormDTO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.BadRequestException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.verbososcorp.ilocation.DAO.OrderDAO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.ResourceNotFoundException;
import br.com.verbososcorp.ilocation.models.GeoLocation;
import br.com.verbososcorp.ilocation.models.Order;
import br.com.verbososcorp.ilocation.services.interfaces.OrderService;

@Component
@Primary
@Qualifier("default")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO dao;

    @Override
    public ResponseEntity<List<Order>> getAll() {
        List<Order> orderList = (List<Order>) dao.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderList);
    }

    @Override
    public ResponseEntity<Order> getByID(Integer id) {
        Optional<Order> order = dao.findById(id);

        if (order.isEmpty()) {
            throw new ResourceNotFoundException("Pedido não encontrado");
        }

        return ResponseEntity.ok(order.get());
    }

    @Override
    public ResponseEntity<List<GeoLocation>> getTracking(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<List<Order>> getByStatus(Integer status) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override

    //0 - Em Aberto: Pedidos aguardando para serem escolhidos pela pessoa entregadora para entrega
    //1 - A Caminho: Após pessoa entregadora pegar o pedido para entrega. Pedido está em rota de entrega
    //2 - Entregue: Pedido entregue ao cliente
    //3 - Cancelado

    public ResponseEntity<Order> changeStatus(OrderChangeStatusFormDTO orderChangeStatusForm) {
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
            return ResponseEntity.status(HttpStatus.CREATED).body(orderToChange);

        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }


}
