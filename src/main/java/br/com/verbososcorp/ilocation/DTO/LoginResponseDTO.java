package br.com.verbososcorp.ilocation.DTO;


import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private String refresh_token;
}
