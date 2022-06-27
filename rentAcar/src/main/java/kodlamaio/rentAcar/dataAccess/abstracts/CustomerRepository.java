package kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.rentAcar.entities.conretes.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}