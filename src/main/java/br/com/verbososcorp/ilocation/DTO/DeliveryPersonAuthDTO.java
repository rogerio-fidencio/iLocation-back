package br.com.verbososcorp.ilocation.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryPersonAuthDTO {
    String emailOrPhone;
    String password;
}
