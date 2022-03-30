package br.com.verbososcorp.ilocation.exceptions.customExceptions;

public class OrderNotFoundException extends Exception{
	
	public OrderNotFoundException() {
		super("Pedido não encontrado.");
	}

}
