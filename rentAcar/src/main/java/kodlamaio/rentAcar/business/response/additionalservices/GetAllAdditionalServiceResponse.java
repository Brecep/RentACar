package kodlamaio.rentAcar.business.response.additionalservices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllAdditionalServiceResponse {
	private int id;
	private String name;
	private double dailyPrice;
}
