package kodlamaio.rentAcar.business.request.additionalItems;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalItemRequest {
	private int id;

	@NotBlank
	@NotNull
	@Size(min = 3, max = 30)
	private String name;

	@NotBlank
	@NotNull
	@Size(min = 3, max = 100)
	private String description;
	private double additionalPrice;
}