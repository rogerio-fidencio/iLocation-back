package br.com.verbososcorp.ilocation.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "delivery_person")
public class DeliveryPerson {

    @Id
    @Column(name = "delivery_person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "delivery_person_name", nullable = false, length = 50)
    private String name;

    @Column(name = "delivery_person_cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "delivery_person_email", nullable = false, length = 100)
    private String email;

    @Column(name = "delivery_person_phone", nullable = false, length = 11)
    private String phone;

    @Column(name = "delivery_person_password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @OneToMany(mappedBy = "deliveryPerson")
    @JsonIgnoreProperties("deliveryPerson")
    private List<Order> orderGroup;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrderGroup() {
		return orderGroup;
	}

	public void setOrderGroup(List<Order> orderGroup) {
		this.orderGroup = orderGroup;
	}
    
    

}
