package br.com.verbososcorp.ilocation.exceptions.customExceptions;

public class OrderNotAvailableException extends Exception{
	
	public OrderNotAvailableException() {
		super("Pedido não disponível para atribuição.");
	}
}
