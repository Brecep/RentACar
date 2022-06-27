package kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import kodlamaio.rentAcar.business.request.corporate.CreateCorporateCustomerRequest;
import kodlamaio.rentAcar.business.request.corporate.DeleteCorporateCustomerRequest;
import kodlamaio.rentAcar.business.request.corporate.UpdateCorporateCustomerRequest;
import kodlamaio.rentAcar.business.response.corporates.GetAllCorporateCustomerResponse;
import kodlamaio.rentAcar.business.response.corporates.GetCorporateCustomerResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

public interface CorporateCustomerService {
	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);

	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);

	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);

	DataResult<GetCorporateCustomerResponse> getById(int id);

	// DataResult<List<GetAllCorporateCustomerResponse>> getAll(int pageNo, int
	// pageSize);

	DataResult<List<GetAllCorporateCustomerResponse>> getAll();
}