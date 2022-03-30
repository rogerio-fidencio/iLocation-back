package br.com.verbososcorp.ilocation.DTO;

public class DeliveryPersonAuthDTO {
    String emailOrPhone;
    String password;

    public DeliveryPersonAuthDTO() {
    }
    
    

	public DeliveryPersonAuthDTO(String emailOrPhone, String password) {
		super();
		this.emailOrPhone = emailOrPhone;
		this.password = password;
	}



	public String getEmailOrPhone() {
		return emailOrPhone;
	}

	public void setEmailOrPhone(String emailOrPhone) {
		this.emailOrPhone = emailOrPhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
