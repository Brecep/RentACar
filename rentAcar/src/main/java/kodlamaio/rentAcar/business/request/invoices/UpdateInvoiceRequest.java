package kodlamaio.rentAcar.business.request.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInvoiceRequest {
	private String invoiceNumber;
	private int rentalId;
}
