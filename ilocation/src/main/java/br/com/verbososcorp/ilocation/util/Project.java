package br.com.verbososcorp.ilocation.util;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Project {
    public static final String BASE_URL = "/api/v1";

    public static Algorithm getAlgorithm(String secret) {
        return Algorithm.HMAC256(secret.getBytes());
    }

    public static Integer getTokenID(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String idString = authentication.getName();
        return Integer.parseInt(idString);
    }
}
