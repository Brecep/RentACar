package kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import kodlamaio.rentAcar.business.request.additionalservices.CreateAdditionalServiceRequest;
import kodlamaio.rentAcar.business.request.additionalservices.DeleteAdditionalServiceRequest;
import kodlamaio.rentAcar.business.request.additionalservices.UpdateAdditionalServiceRequest;
import kodlamaio.rentAcar.business.response.additionalservices.GetAdditionalServiceResponse;
import kodlamaio.rentAcar.business.response.additionalservices.GetAllAdditionalServiceResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

public interface AdditionalService {
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequests);

	Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequests);

	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequests);

	DataResult<List<GetAllAdditionalServiceResponse>> getAll();

	DataResult<GetAdditionalServiceResponse> getById(int id);

}
