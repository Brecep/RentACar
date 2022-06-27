package kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.rentAcar.business.abstracts.InvoiceService;
import kodlamaio.rentAcar.business.request.invoices.CreateInvoceRequest;
import kodlamaio.rentAcar.business.request.invoices.DeleteInvoiceRequest;
import kodlamaio.rentAcar.business.request.invoices.UpdateInvoiceRequest;
import kodlamaio.rentAcar.business.response.invoices.GetAllInvoicesResponse;
import kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import kodlamaio.rentAcar.dataAccess.abstracts.InvoiceRepository;
import kodlamaio.rentAcar.entities.conretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService {
	@Autowired
	private ModelMapperService modelMapperService;

	@Autowired
	private InvoiceRepository invoiceRepository;

	public InvoiceManager(ModelMapperService modelMapperService, InvoiceRepository invoiceRepository) {
		super();
		this.modelMapperService = modelMapperService;
		this.invoiceRepository = invoiceRepository;
	}

	@Override
	public Result add(CreateInvoceRequest createInvoceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoceRequest, Invoice.class);
		invoice.setState(1);
		this.invoiceRepository.save(invoice);
		return new SuccessResult("INVOICE.ADDED");
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		Invoice invoice = invoiceRepository.findById(deleteInvoiceRequest.getId());
		invoice.setState(0);
		this.invoiceRepository.save(invoice);
		return new SuccessResult("INVOICE.CANCEL");
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {

		return null;
	}

	@Override
	public DataResult<List<GetAllInvoicesResponse>> getAll() {
		List<Invoice> invoices = this.invoiceRepository.findAll();
		List<GetAllInvoicesResponse> response = invoices.stream()
				.map(invoice -> this.modelMapperService.forResponse().map(invoice, GetAllInvoicesResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllInvoicesResponse>>(response);
	}

	@Override
	public DataResult<Invoice> getById(int id) {
		return new SuccessDataResult<Invoice>(this.invoiceRepository.findById(id));
	}

}
