package kodlamaio.rentAcar.business.request.rentals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteRentalRequest {
	private int id;
	private int carId;
}
