package kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.rentAcar.entities.conretes.Additional;

public interface AdditionalServiceRepository extends JpaRepository<Additional, Integer> {
	Additional getById(int id);
}
