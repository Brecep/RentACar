package kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.rentAcar.business.abstracts.InvoiceService;
import kodlamaio.rentAcar.business.request.invoices.CreateInvoceRequest;
import kodlamaio.rentAcar.business.request.invoices.DeleteInvoiceRequest;
import kodlamaio.rentAcar.business.response.invoices.GetAllInvoicesResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/invoices/")
public class InvoicesController {

	private InvoiceService invoiceService;

	@Autowired
	public InvoicesController(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@PostMapping("add")
	public Result add(@RequestBody CreateInvoceRequest createInvoiceRequest) {
		return this.invoiceService.add(createInvoiceRequest);
	}

	@PostMapping("delete")
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		return this.invoiceService.delete(deleteInvoiceRequest);
	}

	@GetMapping("getalladditional")
	public DataResult<List<GetAllInvoicesResponse>> additionalGetAll() {
		return null;
	}

	@GetMapping("getallrental")
	public DataResult<List<GetAllInvoicesResponse>> rentalGetAll() {
		return null;
	}
}
