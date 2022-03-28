package br.com.verbososcorp.ilocation.exceptions.customExceptions;

public class AuthExeption extends RuntimeException{

    public AuthExeption(String ErrorMessage){
        super(ErrorMessage);
    }
}
