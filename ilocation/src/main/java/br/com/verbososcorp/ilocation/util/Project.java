package br.com.verbososcorp.ilocation.util;

import br.com.verbososcorp.ilocation.DTO.DeliveryPersonDTO;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Project {
    public static final String BASE_URL = "/api/v1";

    public static Algorithm getAlgorithm(String secret) {
        return Algorithm.HMAC256(secret.getBytes());
    }

    public static DeliveryPersonDTO getContextData() throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String stringData = authentication.getName();
        stringData = stringData.replaceAll("\\\\", "");
        stringData = stringData.substring(1);
        stringData = stringData.substring(0, stringData.length() - 1);
        return new ObjectMapper().readValue(stringData, DeliveryPersonDTO.class);

    }
}
