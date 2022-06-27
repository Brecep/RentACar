package kodlamaio.rentAcar.business.abstracts;

import java.rmi.RemoteException;
import java.util.List;

import kodlamaio.rentAcar.business.request.individuals.CreateIndividualCustomerRequest;
import kodlamaio.rentAcar.business.request.individuals.DeleteIndividualCustomerRequest;
import kodlamaio.rentAcar.business.request.individuals.UpdateIndividualCustomerRequest;
import kodlamaio.rentAcar.business.response.individuals.GetAllIndividualCustomerResponse;
import kodlamaio.rentAcar.business.response.individuals.GetIndividualCustomerResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

public interface IndividualCustomerService {
	Result add(CreateIndividualCustomerRequest createCorporateCustomerRequest)
			throws NumberFormatException, RemoteException;

	Result delete(DeleteIndividualCustomerRequest deleteCorporateCustomerRequest);

	Result update(UpdateIndividualCustomerRequest updateCorporateCustomerRequest);

	DataResult<GetIndividualCustomerResponse> getById(int id);

	DataResult<List<GetAllIndividualCustomerResponse>> getAll(Integer pageNo, Integer pageSize);

	DataResult<List<GetAllIndividualCustomerResponse>> getAll();
}