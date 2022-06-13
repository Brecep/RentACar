package kodlamaio.rentAcar.business.response.brands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBrandsResponse {
	private int id;
	private String name;
}
