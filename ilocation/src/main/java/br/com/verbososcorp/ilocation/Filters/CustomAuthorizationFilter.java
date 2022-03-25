package br.com.verbososcorp.ilocation.Filters;


import br.com.verbososcorp.ilocation.DTO.DeliveryPersonDTO;
import br.com.verbososcorp.ilocation.exceptions.ErrorMessage;
import br.com.verbososcorp.ilocation.models.DeliveryPerson;
import br.com.verbososcorp.ilocation.services.interfaces.DeliveryPersonService;
import br.com.verbososcorp.ilocation.util.Project;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static br.com.verbososcorp.ilocation.util.Project.getAlgorithm;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


public class CustomAuthorizationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals(Project.BASE_URL + "/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(CustomAuthenticationFilter.ATRIBUTO_PREFIXO)) {

            ErrorMessage errorMessage = new ErrorMessage(403, new Date(), "Requisição sem autorização.", request.getServletPath());
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setStatus(403);

            new ObjectMapper().writeValue(response.getOutputStream(), errorMessage);
            filterChain.doFilter(request, response);
            return;
        }

        try{
            String token = authorizationHeader.replace(CustomAuthenticationFilter.ATRIBUTO_PREFIXO, "");

            Algorithm algorithm = getAlgorithm(CustomAuthenticationFilter.TOKEN_SENHA);

            JWTVerifier verifier = JWT.require(algorithm).build();

            DecodedJWT decodedJWT = verifier.verify((token));

            String email = decodedJWT.getSubject();

            Claim user =decodedJWT.getClaim ("user");

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user , null, new ArrayList<>());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);

        } catch (JWTVerificationException | IOException | ServletException e) {

            ErrorMessage errorMessage = new ErrorMessage(403, new Date(), e.getMessage(), request.getServletPath());
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setStatus(403);

            new ObjectMapper().writeValue(response.getOutputStream(), errorMessage);
        }
    }
}
