package br.com.verbososcorp.ilocation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "tb_customer")
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

    public String getCep() {
      return cep;
    }

    public void setCep(String cep) {
      this.cep = cep;
    }

    public String getNumRes() {
      return numRes;
    }

    public void setNumRes(String numRes) {
      this.numRes = numRes;
    }

    public String getCompl() {
      return compl;
    }

    public void setCompl(String compl) {
      this.compl = compl;
    }

    public List<Order> getOrderGroup() {
      return orderGroup;
    }

    public void setOrderGroup(List<Order> orderGroup) {
      this.orderGroup = orderGroup;
    }

}
