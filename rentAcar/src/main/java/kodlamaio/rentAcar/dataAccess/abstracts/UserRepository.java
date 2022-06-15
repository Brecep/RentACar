package kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.rentAcar.entities.conretes.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User getById(int id);
}
