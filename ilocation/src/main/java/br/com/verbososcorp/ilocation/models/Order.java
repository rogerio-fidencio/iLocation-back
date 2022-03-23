package br.com.verbososcorp.ilocation.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer id;
	
	@Column(name = "order_date", nullable = false)
	private LocalDateTime date;
	
	@Column(name = "order_status", nullable = false)
	private Integer status;
	
	@OneToMany(mappedBy = "order")
	@JsonIgnoreProperties("order")
	private List<GeoLocation> geoLocationGroup;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonIgnoreProperties("orderGroup")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "delivery_person_id")
	@JsonIgnoreProperties("orderGroup")
	private DeliveryPerson deliveryPerson;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<GeoLocation> getGeoLocationGroup() {
		return geoLocationGroup;
	}

	public void setGeoLocationGroup(List<GeoLocation> geoLocationGroup) {
		this.geoLocationGroup = geoLocationGroup;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public DeliveryPerson getDeliveryPerson() {
		return deliveryPerson;
	}

	public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}
}
