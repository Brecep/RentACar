package kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import kodlamaio.rentAcar.business.request.rentals.CreateRentalRequest;
import kodlamaio.rentAcar.business.request.rentals.DeleteRentalRequest;
import kodlamaio.rentAcar.business.request.rentals.UpdateRentalRequest;
import kodlamaio.rentAcar.business.response.rentals.GetAllRentalsResponse;
import kodlamaio.rentAcar.business.response.rentals.GetRentalResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

public interface RentalService {
	Result add(CreateRentalRequest createRentalRequest);

	Result delete(DeleteRentalRequest deleteRentalRequest);

	Result update(UpdateRentalRequest updateRentalRequest);

	DataResult<List<GetAllRentalsResponse>> getAll();

	DataResult<GetRentalResponse> getById(int id);
}
