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


}
