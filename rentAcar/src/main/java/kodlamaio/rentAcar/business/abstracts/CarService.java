package kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import kodlamaio.rentAcar.business.request.cars.CreateCarRequest;
import kodlamaio.rentAcar.business.request.cars.DeleteCarRequest;
import kodlamaio.rentAcar.business.request.cars.UpdateCarRequest;
import kodlamaio.rentAcar.business.response.cars.GetAllCarsResponse;
import kodlamaio.rentAcar.business.response.cars.GetByIdCarResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

public interface CarService {
	Result add(CreateCarRequest createCarRequest);

	Result delete(DeleteCarRequest deleteCarRequest);

	Result update(UpdateCarRequest updateCarRequest);

	DataResult<List<GetAllCarsResponse>> getAll();

	DataResult<GetByIdCarResponse> getById(int id);
}
