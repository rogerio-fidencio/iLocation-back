package br.com.verbososcorp.ilocation.Repository;


import br.com.verbososcorp.ilocation.DAO.DeliveryPersonDAO;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.DecimalFormat;

@DataJpaTest
@DisplayName("Tests For DeliveryPerson Repository")
public class DeliveryPersonRepositoryTest {

    @Autowired
    private DeliveryPersonDAO repository;

    @Test
    @DisplayName("Save creates DeliveryPerson when Successful")
        void save_PersistAnime_WhenSuccessful(){
            DeliveryPerson deliveryPersonToBeSaved = createDeliveryPerson();

            DeliveryPerson deliveryPersonSaved = this.repository.save(deliveryPersonToBeSaved);

            Assertions.assertThat(deliveryPersonSaved).isNotNull();
            Assertions.assertThat(deliveryPersonSaved.getId()).isNotNull();
            deliveryPersonToBeSaved.setId(deliveryPersonSaved.getId());
            Assertions.assertThat(deliveryPersonSaved).isEqualTo(deliveryPersonToBeSaved);

        }


    private DeliveryPerson createDeliveryPerson(){
        return new DeliveryPerson(

                "Henrique Lima",
                "02668120071",
                "Henrique@email.com",
                "51995797875",
                "test");

    }
}
