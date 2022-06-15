package kodlamaio.rentAcar.business.response.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {
	private int id;
	private String firstName;
	private String lastName;
	private String tcNo;
	private String eMail;
	private String password;
	private int year;
}
