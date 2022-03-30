package br.com.verbososcorp.ilocation.exceptions.customExceptions;

public class OrderCannotBeConcludedException extends Exception{
	
	public OrderCannotBeConcludedException() {
		super("Status do pedido não pode ser alterado para 'Entregue'.");
	}

}
