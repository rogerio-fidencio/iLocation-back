package br.com.verbososcorp.ilocation.exceptions.customExceptions;

import org.springframework.web.server.ServerErrorException;

public class InternalServerErrorExceptionn extends RuntimeException {

    public InternalServerErrorExceptionn(String message) {
        super(message);
    }
}
