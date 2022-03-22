package br.com.verbososcorp.ilocation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "cliente")
public class Customer {

    @Id
    @Column(name = "costumer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "costumer_name", nullable = false, length = 50)
    private String name;

    @Column(name = "costumer_cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "costumer_email", nullable = false, length = 100)
    private String email;

    @Column(name = "costumer_phone", nullable = false, length = 11)
    private String phone;

    @Column(name = "costumer_cep", nullable = false, length = 9)
    private String cep;

    @Column(name = "costumer_num_res", nullable = false, length = 10)
    private String numRes;

    @Column(name = "costumer_compl", length = 30)
    private String compl;

    @OneToMany(mappedBy = "costumer")
    @JsonIgnoreProperties("costumer")
    List<String> OrderList; // mudar para Order
}
