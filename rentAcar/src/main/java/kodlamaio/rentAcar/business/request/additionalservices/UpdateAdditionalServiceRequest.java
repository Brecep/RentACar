package kodlamaio.rentAcar.business.request.additionalservices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceRequest {
	private int id;
	private String name;
	private double dailyPrice;
}
