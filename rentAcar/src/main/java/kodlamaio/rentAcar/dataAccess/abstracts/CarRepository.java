package kodlamaio.rentAcar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.rentAcar.entities.conretes.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
	List<Car> getByBrandId(int brandId);

	Car findById(int id);
}
