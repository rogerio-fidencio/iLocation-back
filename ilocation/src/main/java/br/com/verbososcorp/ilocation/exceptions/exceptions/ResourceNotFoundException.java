package br.com.verbososcorp.ilocation.exceptions.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    };
}
