package kodlamaio.rentAcar.business.response.additionalservices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAdditionalServiceResponse {
	private int id;
	private int additionalItemId;
	private int rentalId;
}
