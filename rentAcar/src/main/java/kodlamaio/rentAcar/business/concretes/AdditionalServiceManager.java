package kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.rentAcar.business.abstracts.AdditionalService;
import kodlamaio.rentAcar.business.request.additionalservices.CreateAdditionalServiceRequest;
import kodlamaio.rentAcar.business.request.additionalservices.DeleteAdditionalServiceRequest;
import kodlamaio.rentAcar.business.request.additionalservices.UpdateAdditionalServiceRequest;
import kodlamaio.rentAcar.business.response.additionalservices.GetAdditionalServiceResponse;
import kodlamaio.rentAcar.business.response.additionalservices.GetAllAdditionalServiceResponse;
import kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import kodlamaio.rentAcar.dataAccess.abstracts.AdditionalServiceRepository;
import kodlamaio.rentAcar.entities.conretes.Additional;

@Service
public class AdditionalServiceManager implements AdditionalService {

	@Autowired
	private ModelMapperService modelMapperService;
	@Autowired
	private AdditionalServiceRepository additionalServiceRepository;

	/*
	 * public AdditionalServiceManager(ModelMapperService modelMapperService,
	 * AdditionalServiceRepository additionalServiceRepository) { super();
	 * this.modelMapperService = modelMapperService;
	 * this.additionalServiceRepository = additionalServiceRepository; }
	 */

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequests) {
		Additional additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequests,
				Additional.class);
		this.additionalServiceRepository.save(additionalService);
		return new SuccessResult("ADDITIONAL.ADDED");
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequests) {
		this.additionalServiceRepository.deleteById(deleteAdditionalServiceRequests.getId());
		return new SuccessResult("ADDITIONAL.DELETED");
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequests) {
		Additional additional = this.modelMapperService.forRequest().map(updateAdditionalServiceRequests,
				Additional.class);
		this.additionalServiceRepository.save(additional);
		return new SuccessResult("ADDITIONAL.UPDATED");
	}

	@Override
	public DataResult<List<GetAllAdditionalServiceResponse>> getAll() {
		List<Additional> additionals = this.additionalServiceRepository.findAll();
		List<GetAllAdditionalServiceResponse> response = additionals.stream()
				.map(additionalItem -> this.modelMapperService.forResponse().map(additionalItem,
						GetAllAdditionalServiceResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAdditionalServiceResponse>>(response, "ADDITIONALS.LISTED");
	}

	@Override
	public DataResult<GetAdditionalServiceResponse> getById(int id) {
		Additional additional = this.additionalServiceRepository.getById(id);
		GetAdditionalServiceResponse response = this.modelMapperService.forResponse().map(additional,
				GetAdditionalServiceResponse.class);
		return new SuccessDataResult<GetAdditionalServiceResponse>(response);
	}

}
