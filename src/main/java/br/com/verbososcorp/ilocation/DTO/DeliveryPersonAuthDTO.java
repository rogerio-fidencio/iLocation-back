package br.com.verbososcorp.ilocation.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DeliveryPersonAuthDTO {
    String emailOrPhone;
    String password;
}
