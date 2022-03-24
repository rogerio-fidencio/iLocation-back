package br.com.verbososcorp.ilocation.exceptions.customExceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    };
}
