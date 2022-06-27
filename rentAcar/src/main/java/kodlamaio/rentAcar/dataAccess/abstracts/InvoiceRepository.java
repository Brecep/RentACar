package kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.rentAcar.entities.conretes.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
	Invoice findById(int id);
}
