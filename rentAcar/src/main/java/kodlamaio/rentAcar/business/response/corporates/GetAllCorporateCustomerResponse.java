package kodlamaio.rentAcar.business.response.corporates;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCorporateCustomerResponse {
	private int corporateCustomerId;
	private String corporateName;
	private String taxNumber;
	private int customerNumber;
	private String email;
	private String password;
}