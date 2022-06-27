package kodlamaio.rentAcar.business.request.individuals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteIndividualCustomerRequest {
	private int individualCustomerId;
}
