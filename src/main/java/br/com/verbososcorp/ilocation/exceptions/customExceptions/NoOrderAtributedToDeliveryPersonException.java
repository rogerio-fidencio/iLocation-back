package br.com.verbososcorp.ilocation.exceptions.customExceptions;

public class NoOrderAtributedToDeliveryPersonException extends Exception{

		public NoOrderAtributedToDeliveryPersonException() {
			super("Não há pedido atribuído a esta pessoa entregadora.");
		}
}
