package kodlamaio.rentAcar.business.request.rentals;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {
	private Date pickupDate;
	private Date returnDate;
	private int pickUpCityId;
	private int returnCityId;
	private int carId;
	private int userId;
}
