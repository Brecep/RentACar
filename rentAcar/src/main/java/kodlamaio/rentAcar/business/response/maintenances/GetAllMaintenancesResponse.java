package kodlamaio.rentAcar.business.response.maintenances;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllMaintenancesResponse {
	private int id;
	private Date dateSent;
	private Date dateReturned;
	private int carId;
}
