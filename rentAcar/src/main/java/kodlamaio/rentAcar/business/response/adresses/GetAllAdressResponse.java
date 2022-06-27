package kodlamaio.rentAcar.business.response.adresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAdressResponse {
	private int id;
	private String adressName;
}
