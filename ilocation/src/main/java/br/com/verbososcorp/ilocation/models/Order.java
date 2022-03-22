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
@Table(name = "order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", nullable = false)
	private Integer id;
	
	@Column(name = "order_date", nullable = false)
	private LocalDateTime date;
	
	@Column(name = "order_status", nullable = false)
	private Integer status;
	
	@OneToMany(mappedBy = "order")
	@JsonIgnoreProperties("order")
	List<GeoLocation> geoLocationGroup;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonIgnoreProperties("orderGroup")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "delivery_person_id")
	@JsonIgnoreProperties("orderGroup")
	private DeliveryPerson deliveryPerson;
}
