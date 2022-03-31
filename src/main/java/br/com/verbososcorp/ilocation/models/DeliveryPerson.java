package br.com.verbososcorp.ilocation.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_delivery_person")
public class DeliveryPerson {

    @Id
    @Column(name = "delivery_person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@NotEmpty
    @Column(name = "delivery_person_name", nullable = false, length = 50)
    private String name;

	@CPF
	@NotEmpty
    @Column(name = "delivery_person_cpf", nullable = false, length = 11)
    private String cpf;

	@Email
	@NotEmpty
    @Column(name = "delivery_person_email", nullable = false, length = 100)
    private String email;

	@NotEmpty
	@Column(name = "delivery_person_phone", nullable = false, length = 11)
	private String phone;

	@NotEmpty
    @Column(name = "delivery_person_password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @OneToMany(mappedBy = "deliveryPerson")
    @JsonIgnoreProperties("deliveryPerson")
    private List<Order> orderGroup;

	public DeliveryPerson(String name, String cpf, String email, String phone, String password) {
		this.id = null;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.orderGroup = new ArrayList<>();
	}

	public DeliveryPerson() {
	}

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
