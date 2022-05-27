package br.com.verbososcorp.ilocation.IntegrationTests;

import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URISyntaxException;

import static br.com.verbososcorp.ilocation.Util.DeliveryPersonCreator.createDeliveryPersonToBeSaved;
import static br.com.verbososcorp.ilocation.Util.TOKEN.USER_TOKEN;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Order Integration Tests")
@AutoConfigureTestDatabase
public class OrdersIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Register returns DeliveryPerson when sucessful")
    void register_Returns_DeliveryPerson_when_sucessful() throws URISyntaxException {
        DeliveryPerson deliveryPersonToBeSaved = createDeliveryPersonToBeSaved();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", USER_TOKEN);
        HttpEntity<DeliveryPerson> httpEntity = new HttpEntity<>(deliveryPersonToBeSaved, headers);

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
