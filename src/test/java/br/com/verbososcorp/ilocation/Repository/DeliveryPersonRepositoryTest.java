package br.com.verbososcorp.ilocation.Repository;


import br.com.verbososcorp.ilocation.DAO.DeliveryPersonDAO;
import br.com.verbososcorp.ilocation.DTO.DeliveryPersonAuthDTO;
import br.com.verbososcorp.ilocation.Util.DeliveryPersonCreator;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.net.BindException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import static br.com.verbososcorp.ilocation.Util.DeliveryPersonCreator.createDeliveryPersonToBeSaved;

@DataJpaTest
@DisplayName("Tests For DeliveryPerson Repository")
public class DeliveryPersonRepositoryTest {

    @Autowired
    private DeliveryPersonDAO repository;


    @Test
    @DisplayName("Save persist DeliveryPerson when Successful")
        void save_PersistAnime_WhenSuccessful(){
            DeliveryPerson deliveryPersonToBeSaved = createDeliveryPersonToBeSaved();

            DeliveryPerson deliveryPersonSaved = this.repository.save(deliveryPersonToBeSaved);

            Assertions.assertThat(deliveryPersonSaved).isNotNull();
            Assertions.assertThat(deliveryPersonSaved.getId()).isNotNull();
            deliveryPersonToBeSaved.setId(deliveryPersonSaved.getId());
            Assertions.assertThat(deliveryPersonSaved).isEqualTo(deliveryPersonToBeSaved);

        }


        @Test
        @DisplayName("Save updates  DeliveryPerson when Successful")
        void save_Updates_WhenSuccessful(){
            DeliveryPerson deliveryPersonToBeSaved = createDeliveryPersonToBeSaved();

            deliveryPersonToBeSaved.setName("newName");

            DeliveryPerson deliveryPersonSaved = this.repository.save(deliveryPersonToBeSaved);

            Assertions.assertThat(deliveryPersonSaved).isNotNull();
            Assertions.assertThat(deliveryPersonSaved.getId()).isNotNull();
            Assertions.assertThat(deliveryPersonSaved.getName()).isEqualTo("newName");

        }

        @Test
        @DisplayName("Delete removes DeliveryPerson when Successful")
        void save_Delete_WhenSuccessful(){
            DeliveryPerson deliveryPersonToBeSaved = createDeliveryPersonToBeSaved();

            deliveryPersonToBeSaved.setName("newName");

            DeliveryPerson deliveryPersonSaved = this.repository.save(deliveryPersonToBeSaved);

            this.repository.delete(deliveryPersonSaved);

            Optional<DeliveryPerson> personDeleted = this.repository.findById(deliveryPersonSaved.getId());

            Assertions.assertThat(personDeleted.isEmpty()).isTrue();
        }

    @Test
    @DisplayName("Delete removes DeliveryPerson when Successful")
    void delete_revomesDeliveryPerson_WhenSuccessful(){
        DeliveryPerson deliveryPersonToBeSaved = createDeliveryPersonToBeSaved();

        deliveryPersonToBeSaved.setName("newName");

        DeliveryPerson deliveryPersonSaved = this.repository.save(deliveryPersonToBeSaved);

        this.repository.delete(deliveryPersonSaved);

        Optional<DeliveryPerson> personDeleted = this.repository.findById(deliveryPersonSaved.getId());

        Assertions.assertThat(personDeleted.isEmpty()).isTrue();
    }


    @Test
    @DisplayName("Find all by id returns a list of DeliveryPerson when Successful")
    void FindByID_ReturnsListOfDeliveryPerson_WhenSuccessful(){
        DeliveryPerson deliveryPersonToBeSaved = createDeliveryPersonToBeSaved();

        DeliveryPerson deliveryPersonSaved = this.repository.save(deliveryPersonToBeSaved);

        List<DeliveryPerson> list = (List<DeliveryPerson>) this.repository.findAll();

        Assertions.assertThat(list)
                .isNotEmpty()
                .contains(deliveryPersonSaved);

    }

    @Test
    @DisplayName("Find all by id returns a empty list of DeliveryPerson when no person is found")
    void FindByID_ReturnsEmptyList_WhenNoPersonIsFound(){

        List<DeliveryPerson> list = ( List<DeliveryPerson>) this.repository.findAll();

        Assertions.assertThat(list).isEmpty();
    }

    @Test
    @DisplayName("Save trhow ConstraintValidationException when name is empty")
    void save_ThrowsCostraintValidationException_WhenNameIsEmpty(){

        DeliveryPerson deliveryPerson = createDeliveryPersonToBeSaved();

        deliveryPerson.setName("");

        Assertions.assertThatThrownBy(() -> this.repository.save(deliveryPerson))
                .isInstanceOf(ValidationException.class);
    }

    @Test
    @DisplayName("Find DeliveryPersonAuthDTO By Email Or Phone returns DeliveryPersonAuthDTO when Sucessful")
    void findDeliveryPersonAuthDTOByEmailOrPhone_returnsDeliveryPersonAutgDTO_WhenSucessful(){
        DeliveryPerson deliveryPersonToBeSaved = createDeliveryPersonToBeSaved();

        DeliveryPerson deliveryPersonSaved = this.repository.save(deliveryPersonToBeSaved);

        Optional<DeliveryPersonAuthDTO> deliveryPersonAuthDTOByPhone =
                this.repository.findDeliveryPersonAuthDTOByEmailOrPhone(deliveryPersonSaved.getPhone());
        Optional<DeliveryPersonAuthDTO> deliveryPersonAuthDTOByEmail =
                this.repository.findDeliveryPersonAuthDTOByEmailOrPhone(deliveryPersonSaved.getEmail());


        Assertions.assertThat(deliveryPersonAuthDTOByPhone.isPresent()).isTrue();
        Assertions.assertThat(deliveryPersonAuthDTOByEmail.isPresent()).isTrue();

        Assertions.assertThat(
                deliveryPersonAuthDTOByEmail.get().getEmailOrPhone().equals(deliveryPersonSaved.getEmail())).isTrue();
        Assertions.assertThat(
                deliveryPersonAuthDTOByEmail.get().getEmailOrPhone().equals(deliveryPersonSaved.getEmail())).isTrue();
    }

    @Test
    @DisplayName("Find DeliveryPersonAuthDTO By Email Or Phone returns DeliveryPersonAuthDTO when Not Found")
    void findDeliveryPersonAuthDTOByEmailOrPhone_returnsDeliveryPersonAutgDTO_WhenNotFound(){
        DeliveryPerson deliveryPersonToBeSaved = createDeliveryPersonToBeSaved();

        DeliveryPerson deliveryPersonSaved = this.repository.save(deliveryPersonToBeSaved);

        Optional<DeliveryPersonAuthDTO> deliveryPersonAuthDTOByPhone =
                this.repository.findDeliveryPersonAuthDTOByEmailOrPhone("WrongNumberOrEmail");
        Assertions.assertThat(deliveryPersonAuthDTOByPhone.isEmpty()).isTrue();


    }
}
