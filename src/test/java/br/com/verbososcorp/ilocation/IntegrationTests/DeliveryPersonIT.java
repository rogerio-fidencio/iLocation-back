package br.com.verbososcorp.ilocation.IntegrationTests;


import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;


import static br.com.verbososcorp.ilocation.Util.DeliveryPersonCreator.createDeliveryPersonToBeSaved;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("DeliveryPerson Integration Tests")
@AutoConfigureTestDatabase
public class DeliveryPersonIT{

    @Autowired
    private TestRestTemplate testRestTemplate;


//    @BeforeEach
//    void setUp() {
//         DeliveryPersonAuthDTO loginForm = DeliveryPersonAuthDTO
//                .builder()
//                .emailOrPhone("Henrique@Email.com")
//                .password("henrique")
//                .build();
//        System.out.println(loginForm);
//         HttpEntity<DeliveryPersonAuthDTO> httpEntity = new HttpEntity<>(loginForm);
//
//         ResponseEntity<LoginResponseDTO> response = testRestTemplate.exchange("/api/v1/login",
//                HttpMethod.POST, httpEntity, LoginResponseDTO.class);
//    }


    @Test
    @DisplayName("Register returns DeliveryPerson when sucessful")
    void register_Returns_DeliveryPerson_when_sucessful() {
        DeliveryPerson deliveryPersonToBeSaved = createDeliveryPersonToBeSaved();

        HttpEntity<DeliveryPerson> httpEntity = new HttpEntity<>(deliveryPersonToBeSaved);

       ResponseEntity<DeliveryPerson> response = this.testRestTemplate.exchange("/api/v1/deliveryperson",
              HttpMethod.PUT, httpEntity, DeliveryPerson.class);

       DeliveryPerson deliveryPersonSaved = response.getBody();

       Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Assertions.assertThat(deliveryPersonSaved).isNotNull();

        Assertions.assertThat(deliveryPersonSaved.getId()).isNotNull();

        Assertions.assertThat(deliveryPersonSaved.getPassword()
                .equals(deliveryPersonToBeSaved.getPassword())).isFalse();

        Assertions.assertThat(deliveryPersonSaved.getName()).isEqualTo(deliveryPersonToBeSaved.getName());
    }
}