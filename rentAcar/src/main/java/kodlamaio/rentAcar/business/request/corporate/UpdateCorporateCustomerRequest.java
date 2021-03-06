package kodlamaio.rentAcar.business.request.corporate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UpdateCorporateCustomerRequest {
	private int corporateCustomerId;
	private String corporateName;
	private String taxNumber;
	private int customerNumber;
	private String email;
	private String password;
}