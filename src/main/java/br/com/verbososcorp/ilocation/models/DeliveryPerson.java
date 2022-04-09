package br.com.verbososcorp.ilocation.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
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
	@ToString.Exclude
	private List<Order> orderGroup;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		DeliveryPerson that = (DeliveryPerson) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
