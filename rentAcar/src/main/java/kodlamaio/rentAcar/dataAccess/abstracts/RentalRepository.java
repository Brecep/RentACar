package kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.rentAcar.entities.conretes.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer>{
	Rental findById(int id);
}
