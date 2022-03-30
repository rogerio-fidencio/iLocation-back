package br.com.verbososcorp.ilocation.exceptions.customExceptions;

public class DeliveryPersonNotAvailableException extends Exception{
	
	public DeliveryPersonNotAvailableException() {
		super("Pessoa entregadora não disponível para atribuição.");
	}

}
