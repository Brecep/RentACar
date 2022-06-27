package kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.rentAcar.entities.conretes.IndividualCustomer;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Integer> {

	IndividualCustomer findByNationality(String nationality);

	IndividualCustomer findByEmail(String email);
}