package br.com.verbososcorp.ilocation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_name", nullable = false, length = 50)
    private String name;

    @Column(name = "customer_cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "customer_email", nullable = false, length = 100)
    private String email;

    @Column(name = "customer_phone", nullable = false, length = 11)
    private String phone;

    @Column(name = "customer_cep", nullable = false, length = 9)
    private String cep;

    @Column(name = "customer_num_res", nullable = false, length = 10)
    private String numRes;

    @Column(name = "customer_compl", length = 30)
    private String compl;

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties("customer")
    List<Order> orderGroup;

}
