package kodlamaio.rentAcar.entities.conretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities")
public class City {
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	private int cityId;

	@Column(name = "city_name")
	private String cityName;

	@OneToMany(mappedBy = "pickCity") // bir şehirde birden fazla araba kiralanır
	private List<Rental> pickRentals;

	@OneToMany(mappedBy = "returnCity")
	private List<Rental> returnRentals;

	@OneToOne(mappedBy = "adresses")
	private Adress adress;
}
