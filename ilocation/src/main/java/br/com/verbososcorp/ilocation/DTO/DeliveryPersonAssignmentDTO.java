package br.com.verbososcorp.ilocation.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DeliveryPersonAssignmentDTO {
	
	
	@NotEmpty
	@NotNull
	Integer orderID;
	
	@NotEmpty
	@NotNull
	Integer deliveryPersonID;
	
	

	public DeliveryPersonAssignmentDTO(Integer orderID, Integer deliveryPersonID) {
		super();		
		this.orderID = orderID;			
		this.deliveryPersonID = deliveryPersonID;
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public Integer getDeliveryPersonID() {
		return deliveryPersonID;
	}

	public void setDeliveryPersonID(Integer deliveryPersonID) {
		this.deliveryPersonID = deliveryPersonID;
	}
	
	

}
