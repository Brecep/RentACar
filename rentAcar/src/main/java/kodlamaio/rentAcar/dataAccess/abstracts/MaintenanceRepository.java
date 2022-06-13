package kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.rentAcar.entities.conretes.Maintenance;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
	Maintenance findById(int id);
}
