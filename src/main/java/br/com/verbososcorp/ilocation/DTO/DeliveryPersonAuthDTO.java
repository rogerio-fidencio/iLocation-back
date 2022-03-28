package br.com.verbososcorp.ilocation.DTO;

public class DeliveryPersonAuthDTO {
    String emailOrCPF;
    String password;

    public DeliveryPersonAuthDTO() {
    }

    public DeliveryPersonAuthDTO(String emailOrCPF, String password) {
        this.emailOrCPF = emailOrCPF;
        this.password = password;
    }

    public String getEmailOrCPF() {
        return emailOrCPF;
    }

    public void setEmailOrCPF(String emailOrCPF) {
        this.emailOrCPF = emailOrCPF;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
