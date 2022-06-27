package kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlamaio.rentAcar.business.abstracts.CorporateCustomerService;
import kodlamaio.rentAcar.business.request.corporate.CreateCorporateCustomerRequest;
import kodlamaio.rentAcar.business.request.corporate.DeleteCorporateCustomerRequest;
import kodlamaio.rentAcar.business.request.corporate.UpdateCorporateCustomerRequest;
import kodlamaio.rentAcar.business.response.corporates.GetAllCorporateCustomerResponse;
import kodlamaio.rentAcar.business.response.corporates.GetCorporateCustomerResponse;
import kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import kodlamaio.rentAcar.dataAccess.abstracts.CorporateCustomerRepository;
import kodlamaio.rentAcar.entities.conretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	private CorporateCustomerRepository corporateCustomerRepository;
	private ModelMapperService modelMapperService;

	public CorporateCustomerManager(CorporateCustomerRepository corporateCustomerRepository,
			ModelMapperService modelMapperService) {
		this.corporateCustomerRepository = corporateCustomerRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		checkCorporateExistsTaxNumber(createCorporateCustomerRequest.getTaxNumber());
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("CORPORATE.ADDED");
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		checkCorporateExists(deleteCorporateCustomerRequest.getCorporateCustomerId());
		this.corporateCustomerRepository.deleteById(deleteCorporateCustomerRequest.getCorporateCustomerId());
		return new SuccessResult("CORPORATE.DELETED");
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		checkCorporateExists(updateCorporateCustomerRequest.getCorporateCustomerId());
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("CORPORATE.UPDATED");
	}

	@Override
	public DataResult<GetCorporateCustomerResponse> getById(int id) {
		checkCorporateExists(id);
		CorporateCustomer corporateCustomer = this.corporateCustomerRepository.findById(id).get();

		GetCorporateCustomerResponse response = this.modelMapperService.forResponse().map(corporateCustomer,
				GetCorporateCustomerResponse.class);

		return new SuccessDataResult<GetCorporateCustomerResponse>(response, "CORPORATE.LISTED");
	}

	@Override
	public DataResult<List<GetAllCorporateCustomerResponse>> getAll() {
		List<CorporateCustomer> users = this.corporateCustomerRepository.findAll();

		List<GetAllCorporateCustomerResponse> response = users.stream()
				.map(user -> this.modelMapperService.forResponse().map(user, GetAllCorporateCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCorporateCustomerResponse>>(response, "CORPORATE.LISTED");
	}

	private void checkCorporateExistsTaxNumber(String taxNumber) {
		CorporateCustomer corporateCustomer = this.corporateCustomerRepository.findByTaxNumber(taxNumber);
		if (corporateCustomer != null) {
			throw new BusinessException("CORPORATE.ALREADY.ADDED");
		}
	}

	private void checkCorporateExists(int id) {
		CorporateCustomer corporateCustomer = this.corporateCustomerRepository.findById(id).get();
		if (corporateCustomer == null) {
			throw new BusinessException("CORPORATE.WAS.NOT.FOUND");
		}
	}

}
