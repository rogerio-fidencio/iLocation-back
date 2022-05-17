package br.com.verbososcorp.ilocation.controller;

import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import br.com.verbososcorp.ilocation.services.interfaces.DeliveryPersonService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static br.com.verbososcorp.ilocation.Util.DeliveryPersonCreator.createDeliveryPersonToBeSaved;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
class DeliveryPersonControllerTest {

    @InjectMocks //classe testada
    private DeliveryPersonController controller;

    @Mock //dependencias
    private DeliveryPersonService service;

    @BeforeEach
    void setUp(){
        DeliveryPerson deliveryPerson = createDeliveryPersonToBeSaved();
        BDDMockito.when(service.register(ArgumentMatchers.any(DeliveryPerson.class)))
                .thenReturn(deliveryPerson);
    }

    @Test
    @DisplayName("PutDeliveryPerson returns delivery Person Saved when successful")
    void PutDeliveryPerson_ReturnsDeliveryPersonSaved_WhenSuccessful(){
        String nameExpected = createDeliveryPersonToBeSaved().getName();

        ResponseEntity<DeliveryPerson> deliveryPersonSavedResponse =
                controller.putDeliveryPerson(createDeliveryPersonToBeSaved());

        Assertions.assertThat(deliveryPersonSavedResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Assertions.assertThat(deliveryPersonSavedResponse.getBody()).isNotNull();

        Assertions.assertThat(deliveryPersonSavedResponse.getBody().getName()).isEqualTo(nameExpected);
    }
}