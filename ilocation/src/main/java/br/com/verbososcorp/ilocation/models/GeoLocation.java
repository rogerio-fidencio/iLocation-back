package br.com.verbososcorp.ilocation.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "geolocation")
public class GeoLocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id", nullable = false)
	private Integer id;
	
	@Column(name = "timastamp", nullable = false)
	private Timestamp timestamp;
	
	@Column(name = "latitude", nullable = false)
	private String latitude;
	
	@Column(name = "longitude", nullable = false)
	private String longitude;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	@JsonIgnoreProperties("geoLocationGroup")
	private Order order;


}
