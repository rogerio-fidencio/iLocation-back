package br.com.verbososcorp.ilocation.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.verbososcorp.ilocation.DAO.DeliveryPersonDAO;
import br.com.verbososcorp.ilocation.DTO.DeliveryPersonAuthDTO;
import br.com.verbososcorp.ilocation.DTO.DeliveryPersonDTO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.DeliveryPersonNotFoundException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.InternalServerErrorException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.ResourceNotFoundException;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import br.com.verbososcorp.ilocation.services.interfaces.DeliveryPersonService;


@Component
@Primary
@Qualifier("default")
public class DeliveryPersonServiceImpl implements DeliveryPersonService, UserDetailsService {

    @Autowired
    private DeliveryPersonDAO dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String emailOrPhone) throws UsernameNotFoundException {
        Optional<DeliveryPersonAuthDTO> userOptional = dao.findDeliveryPersonAuthDTOByEmailOrPhone(emailOrPhone);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("Dados incorretos.");
        }

        DeliveryPersonAuthDTO user = userOptional.get();

        return new User(user.getEmailOrPhone(), user.getPassword(), new ArrayList<>());
    }


    @Override
    public DeliveryPerson register(DeliveryPerson newDeliveryPerson) {

        newDeliveryPerson.setPassword(passwordEncoder.encode(newDeliveryPerson.getPassword()));
        
        return dao.save(newDeliveryPerson);
    }
    

    @Override
    public DeliveryPerson getByEmail(String email) {
        try {
            Optional<DeliveryPerson> deliveryPersonOptional = dao.findByEmail(email);

            if (deliveryPersonOptional.isEmpty()) {
                throw new ResourceNotFoundException("Pessoa entregadora não encontrada!");
            }

            return deliveryPersonOptional.get();

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());

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
    public DeliveryPersonDTO findDeliveryPersonDTOByEmailOrPhone(String emailOrPhone) {
    	//TODO revisar os catches.
        try {
            Optional<DeliveryPersonDTO> deliveryPersonOptional = dao.findDeliveryPersonDTOByEmailOrPhone(emailOrPhone);
            if (deliveryPersonOptional.isEmpty()) {
                throw new ResourceNotFoundException("Pessoa entregadora não encontrada!");
            }

            DeliveryPersonDTO deliveryPerson = deliveryPersonOptional.get();

            return new DeliveryPersonDTO(deliveryPerson.getId(), deliveryPerson.getName(), deliveryPerson.getPhone());

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());

        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

}