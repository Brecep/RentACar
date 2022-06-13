package kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.rentAcar.entities.conretes.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
	Brand findById(int id);

	Brand findByName(String name);
}