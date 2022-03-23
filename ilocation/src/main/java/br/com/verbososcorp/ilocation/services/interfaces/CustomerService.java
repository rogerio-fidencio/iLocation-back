package br.com.verbososcorp.ilocation.services.interfaces;

import br.com.verbososcorp.ilocation.models.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;


public interface CustomerService {

    public ResponseEntity<List<Customer>> getall();

}
