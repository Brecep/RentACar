package kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import kodlamaio.rentAcar.business.request.invoices.CreateInvoceRequest;
import kodlamaio.rentAcar.business.request.invoices.DeleteInvoiceRequest;
import kodlamaio.rentAcar.business.request.invoices.UpdateInvoiceRequest;
import kodlamaio.rentAcar.business.response.invoices.GetAllInvoicesResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.entities.conretes.Invoice;

public interface InvoiceService {
	Result add(CreateInvoceRequest createInvoceRequest);

	Result delete(DeleteInvoiceRequest deleteInvoiceRequest);

	Result update(UpdateInvoiceRequest updateInvoiceRequest);

	DataResult<List<GetAllInvoicesResponse>> getAll();

	DataResult<Invoice> getById(int id);
}
