package br.com.verbososcorp.ilocation.services.interfaces;

import br.com.verbososcorp.ilocation.DAO.DeliveryPersonDAO;
import br.com.verbososcorp.ilocation.DTO.DeliveryPersonAuthDTO;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.DeliveryPersonNotFoundException;
import br.com.verbososcorp.ilocation.exceptions.customExceptions.ResourceNotFoundException;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import br.com.verbososcorp.ilocation.services.Impl.DeliveryPersonServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static br.com.verbososcorp.ilocation.Util.DeliveryPersonCreator.createDeliveryPersonAuthDTO;
import static br.com.verbososcorp.ilocation.Util.DeliveryPersonCreator.createDeliveryPersonToBeSaved;

@ExtendWith(SpringExtension.class)
class DeliveryPersonServiceTest {

    @InjectMocks
    DeliveryPersonServiceImpl deliveryPersonService;

    @Mock
    DeliveryPersonDAO repositoryMocked;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp(){
        DeliveryPerson deliveryPerson = createDeliveryPersonToBeSaved();
        BDDMockito.when(repositoryMocked.save(ArgumentMatchers.any()))
                .thenReturn(deliveryPerson);

        BDDMockito.when(repositoryMocked.findDeliveryPersonAuthDTOByEmailOrPhone(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(createDeliveryPersonAuthDTO()));

        BDDMockito.when(passwordEncoder.encode(ArgumentMatchers.anyString()))
                .thenReturn(deliveryPerson.getPassword());
    }

    @Test
    @DisplayName("LoadByUserName returns DeliveryPersonAuthDTO when sucessful")
    void findDeliveryPersonAuthDTOByEmailOrPhone_ReturnsDeliveryPersonAuthDTO_WhenSucessful() throws DeliveryPersonNotFoundException {

        DeliveryPersonAuthDTO deliveryPersonAuthDTO = createDeliveryPersonAuthDTO();

        UserDetails deliveryPersonDetails =
                this.deliveryPersonService.loadUserByUsername("email");

        Assertions.assertThat(deliveryPersonDetails).isNotNull();

        Assertions.assertThat(deliveryPersonDetails.getUsername().equals(deliveryPersonAuthDTO.getEmailOrPhone())).isTrue();
    }

    @Test
    @DisplayName("LoadByUserName throw ResourceNotFoundException when NotFound")
    void findDeliveryPersonAuthDTOByEmailOrPhone_throwResourceNotFoundException_WhenNotFound() throws DeliveryPersonNotFoundException {

        BDDMockito.when(repositoryMocked.findDeliveryPersonAuthDTOByEmailOrPhone(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> this.deliveryPersonService.findDeliveryPersonDTOByEmailOrPhone("email"))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("Register returns DeliveryPerson when sucessful")
    void register_Returns_DeliveryPerson_when_sucessful(){

        DeliveryPerson deliveryPersonToBeSaved = createDeliveryPersonToBeSaved();

        DeliveryPerson deliveryPersonSaved = deliveryPersonService.register(deliveryPersonToBeSaved);

        Assertions.assertThat(deliveryPersonSaved).isNotNull();

        deliveryPersonToBeSaved.setId(deliveryPersonSaved.getId());

        Assertions.assertThat(deliveryPersonSaved.getPassword()
                .equals(deliveryPersonToBeSaved.getPassword())).isTrue();

        Assertions.assertThat(deliveryPersonSaved.getName()).isEqualTo(deliveryPersonToBeSaved.getName());
    }
}