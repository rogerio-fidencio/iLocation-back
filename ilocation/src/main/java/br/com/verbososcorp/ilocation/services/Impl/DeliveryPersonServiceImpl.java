package br.com.verbososcorp.ilocation.services.Impl;

import br.com.verbososcorp.ilocation.DAO.DeliveryPersonDAO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.ResourceNotFoundException;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import br.com.verbososcorp.ilocation.services.interfaces.DeliveryPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DeliveryPerson> register(DeliveryPerson newDeliveryPerson) {
        newDeliveryPerson.setPassword(passwordEncoder.encode(newDeliveryPerson.getPassword()));

        dao.save(newDeliveryPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDeliveryPerson);
    }

    @Override
    public ResponseEntity<DeliveryPerson> getByEmail(String email) {
        Optional<DeliveryPerson> deliveryPersonOptional = dao.findByEmail(email);

        if (deliveryPersonOptional.isEmpty()) {
            throw new ResourceNotFoundException("Pessoa entregadora não encontrada!");
        }

        return ResponseEntity.ok(deliveryPersonOptional.get());
    }

    @Override
    public ResponseEntity<List<DeliveryPerson>> getAll() {
        return null;
    }

}