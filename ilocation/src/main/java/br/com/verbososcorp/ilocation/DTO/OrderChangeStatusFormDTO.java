package br.com.verbososcorp.ilocation.DTO;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OrderChangeStatusFormDTO {

    @NotNull
    Integer orderID;

    @NotNull()
    @Max(value = 4, message = "Status inválido")
    @Min(value = 0, message = "Status inválido")
    int status;

    public OrderChangeStatusFormDTO(Integer orderID, Integer status) {
        this.orderID = orderID;
        this.status = status;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
