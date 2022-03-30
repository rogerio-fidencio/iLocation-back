package br.com.verbososcorp.ilocation.exceptions.customExceptions;

public class DeliveryPersonNotFoundException extends Exception{
	public DeliveryPersonNotFoundException() {
		super("Pessoa Entregadora não encontrada.");
	}
}
