package br.com.verbososcorp.ilocation.exceptions.customExceptions;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String message) {
        super(message);
    }
}
