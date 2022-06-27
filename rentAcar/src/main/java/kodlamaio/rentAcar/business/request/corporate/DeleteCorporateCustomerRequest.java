package kodlamaio.rentAcar.business.request.corporate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCorporateCustomerRequest {
	private int corporateCustomerId;
}
