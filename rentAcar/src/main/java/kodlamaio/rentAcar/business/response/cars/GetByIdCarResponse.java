package kodlamaio.rentAcar.business.response.cars;


import java.awt.Color;

import kodlamaio.rentAcar.entities.conretes.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdCarResponse {
	private int id;
	private String description;
	private double dailyPrice;
	private String plate;
	private int km;
	private int state;
	private Brand brand;
	private Color color;
}
