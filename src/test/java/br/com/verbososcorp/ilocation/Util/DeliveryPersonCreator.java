package br.com.verbososcorp.ilocation.Util;

import br.com.verbososcorp.ilocation.DTO.DeliveryPersonAuthDTO;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import lombok.Getter;
import lombok.Setter;

public class DeliveryPersonCreator {

    public static DeliveryPerson createDeliveryPersonToBeSaved(){
        return DeliveryPerson.builder()
                .name("Henrique Lima")
                .cpf("02668120071")
                .email("Henrique@email.com")
                .phone("51995797875")
                .password("test").build();

    }

    public static DeliveryPerson createValidDeliveryPerson(){
        return DeliveryPerson.builder()
                .id(123523)
                .name("Henrique Lima")
                .cpf("02668120071")
                .email("Henrique@email.com")
                .phone("51995797875")
                .password("test").build();
    }

    public static DeliveryPerson createDeliveryPersonWithoutName(){
        return DeliveryPerson.builder()
                .id(132)
                .cpf("02668120071")
                .email("Henrique@email.com")
                .phone("51995797875")
                .password("test").build();
    }

    public static DeliveryPersonAuthDTO createDeliveryPersonAuthDTO(){
        return  DeliveryPersonAuthDTO.builder()
                .emailOrPhone("test@test.com")
                .password("test")
                .build();
    }

}
