package br.com.verbososcorp.ilocation.exceptions.customExceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message){
        super(message);
    }
}
