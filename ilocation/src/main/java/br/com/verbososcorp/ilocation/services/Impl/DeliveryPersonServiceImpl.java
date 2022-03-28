package br.com.verbososcorp.ilocation.services.Impl;

import br.com.verbososcorp.ilocation.DAO.DeliveryPersonDAO;
import br.com.verbososcorp.ilocation.DTO.DeliveryPersonDTO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.InternalServerErrorException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.ResourceNotFoundException;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import br.com.verbososcorp.ilocation.services.interfaces.DeliveryPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
@Primary
@Qualifier("default")
public class DeliveryPersonServiceImpl implements DeliveryPersonService, UserDetailsService {

    @Autowired
    private DeliveryPersonDAO dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<DeliveryPerson> userOptional = dao.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Dados incorretos");
        }

        DeliveryPerson user = userOptional.get();

        //aqui pegar as roles/permitions

        return new User(user.getEmail(), user.getPassword(), new ArrayList<>()); // permitions iriam no array vazio
    }


    @Override
    public DeliveryPerson register(DeliveryPerson newDeliveryPerson) {
        try {
            newDeliveryPerson.setPassword(passwordEncoder.encode(newDeliveryPerson.getPassword()));

            dao.save(newDeliveryPerson);
            return newDeliveryPerson;
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public DeliveryPerson getByEmail(String email) {
        try {
            Optional<DeliveryPerson> deliveryPersonOptional = dao.findByEmail(email);

            if (deliveryPersonOptional.isEmpty()) {
                throw new ResourceNotFoundException("Pessoa entregadora não encontrada!");
            }
            return deliveryPersonOptional.get();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public List<DeliveryPerson> getAll() {
        try {
            return (List<DeliveryPerson>) dao.findAll();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public DeliveryPersonDTO getByEmailForAuth(String email) {
        try {
            Optional<DeliveryPerson> deliveryPersonOptional = dao.findByEmail(email);

            if (deliveryPersonOptional.isEmpty()) {
                throw new ResourceNotFoundException("Pessoa entregadora não encontrada!");
            }
            DeliveryPerson deliveryPerson = deliveryPersonOptional.get();

            return new DeliveryPersonDTO(deliveryPerson.getId(), deliveryPerson.getName(), deliveryPerson.getPhone());
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

}