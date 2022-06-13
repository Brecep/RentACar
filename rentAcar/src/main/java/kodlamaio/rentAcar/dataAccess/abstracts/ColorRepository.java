package kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.rentAcar.entities.conretes.Color;

public interface ColorRepository extends JpaRepository<Color, Integer> {
	Color findById(int id);
}
