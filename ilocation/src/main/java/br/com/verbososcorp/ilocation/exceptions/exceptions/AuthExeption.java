package br.com.verbososcorp.ilocation.exceptions.exceptions;

public class AuthExeption extends RuntimeException{

    public AuthExeption(String ErrorMessage){
        super(ErrorMessage);
    }
}
