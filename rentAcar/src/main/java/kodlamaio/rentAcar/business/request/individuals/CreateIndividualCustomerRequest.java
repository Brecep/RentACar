package kodlamaio.rentAcar.business.request.individuals;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIndividualCustomerRequest {
	private String firstName;
	private String lastName;
	private String nationality;
	private LocalDate birthDate;
	private int customerNumber;
	private String email;
	private String password;
}
