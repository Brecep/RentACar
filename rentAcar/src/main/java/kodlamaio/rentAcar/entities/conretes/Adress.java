package kodlamaio.rentAcar.entities.conretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "adresses")
public class Adress {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "adress_name")
	private String adressName;

	@Column(name = "adress")
	private String adress;

	@Column(name = "district")
	private String district;

	@OneToOne
	@JoinColumn(name = "city_id")
	private int city_id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User userId;
}
