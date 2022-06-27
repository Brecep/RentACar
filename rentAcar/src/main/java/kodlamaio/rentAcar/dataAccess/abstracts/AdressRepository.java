package kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.rentAcar.entities.conretes.Adress;

public interface AdressRepository extends JpaRepository<Adress, Integer> {
	Adress getById(int id);
}
