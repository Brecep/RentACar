package kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.rentAcar.entities.conretes.AdditionalItem;

public interface AdditionalItemRepository extends JpaRepository<AdditionalItem, Integer> {
	AdditionalItem getById(int id);

	AdditionalItem findByName(String name);
}
