package kodlamaio.rentAcar.business.request.adresses;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdressRequest {
	private int id;
	private String adressName;
	@Size(min = 10, max = 100)
	@NotNull
	private String adress;
	private String district;
	private int userId;
}
