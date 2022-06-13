package kodlamaio.rentAcar.business.response.cars;

import kodlamaio.rentAcar.entities.conretes.Brand;
import kodlamaio.rentAcar.entities.conretes.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCarsResponse {
	private int id;
	private String description;
	private double dailyPrice;
	private String plate;
	private int km;
	private int state;
	private Brand brand;
	private Color color;
}
