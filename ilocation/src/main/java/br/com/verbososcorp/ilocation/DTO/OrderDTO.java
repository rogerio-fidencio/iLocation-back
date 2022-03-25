package br.com.verbososcorp.ilocation.DTO;

public class OrderDTO {
	
	Integer id;
	Integer status;
	Integer deliveryPersonId;
	
	
	public OrderDTO() {

	}
	
	public OrderDTO(Integer id, Integer status, Integer deliveryPersonId) {
		super();
		this.id = id;
		this.status = status;
		this.deliveryPersonId = deliveryPersonId;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDeliveryPersonId() {
		return deliveryPersonId;
	}
	public void setDeliveryPersonId(Integer deliveryPersonId) {
		this.deliveryPersonId = deliveryPersonId;
	}
	
		
	
	
	

}
