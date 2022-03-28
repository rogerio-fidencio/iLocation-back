package br.com.verbososcorp.ilocation.DTO;

import br.com.verbososcorp.ilocation.models.Order;

import java.time.LocalDateTime;

public class OrderDTO {
	
	Integer id;
	Integer status;
	Integer deliveryPersonId;
	LocalDateTime date;
	Integer customerID;
	String customerName;
	String customerCep;
	String customerNumRes;
	String customerCompl;
		
	
	public OrderDTO() {

	}
	
	public OrderDTO(Integer id, Integer status, Integer deliveryPersonId) {
		super();
		this.id = id;
		this.status = status;
		this.deliveryPersonId = deliveryPersonId;
	}	
	

	public OrderDTO(Integer id, Integer status, Integer deliveryPersonId, LocalDateTime date, Integer customerID,
			String customerName, String customerCep, String customerNumRes, String customerCompl) {
		super();
		this.id = id;
		this.status = status;
		this.deliveryPersonId = deliveryPersonId;
		this.date = date;
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerCep = customerCep;
		this.customerNumRes = customerNumRes;
		this.customerCompl = customerCompl;
	}
	
	public OrderDTO(Order ord) {
		super();
		this.id = ord.getId();
		this.status = ord.getStatus();
		this.deliveryPersonId = ord.getDeliveryPerson().getId();
		this.date = ord.getDate();
		this.customerID = ord.getCustomer().getId();
		this.customerName = ord.getCustomer().getName();
		this.customerCep = ord.getCustomer().getCep();
		this.customerNumRes = ord.getCustomer().getNumRes();
		this.customerCompl = ord.getCustomer().getCompl();
		
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Integer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCep() {
		return customerCep;
	}

	public void setCustomerCep(String customerCep) {
		this.customerCep = customerCep;
	}

	public String getCustomerNumRes() {
		return customerNumRes;
	}

	public void setCustomerNumRes(String customerNumRes) {
		this.customerNumRes = customerNumRes;
	}

	public String getCustomerCompl() {
		return customerCompl;
	}

	public void setCustomerCompl(String customerCompl) {
		this.customerCompl = customerCompl;
	}
	

}
