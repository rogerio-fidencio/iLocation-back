package br.com.verbososcorp.ilocation.exceptions.customExceptions;

public class OrderCannotBeCancelledException extends Exception{
	
	public OrderCannotBeCancelledException() {
		super("Pedido não pode ser cancelado.");
	}
	

}
