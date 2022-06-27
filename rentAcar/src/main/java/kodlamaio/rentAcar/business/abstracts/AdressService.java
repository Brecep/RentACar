package kodlamaio.rentAcar.business.abstracts;

import kodlamaio.rentAcar.business.request.adresses.CreateAdressRequest;
import kodlamaio.rentAcar.business.request.adresses.DeleteAdressRequest;
import kodlamaio.rentAcar.business.request.adresses.UpdateAdressRequest;
import kodlamaio.rentAcar.business.response.adresses.GetAllAdressResponse;
import kodlamaio.rentAcar.business.response.adresses.GetByAdressResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import java.util.List;

public interface AdressService {
	Result add(CreateAdressRequest createAdressRequest);

	Result delete(DeleteAdressRequest deleteAdressRequest);

	Result update(UpdateAdressRequest updateAdressRequest);

	DataResult<List<GetAllAdressResponse>> getAll();

	DataResult<GetByAdressResponse> getById(int id);
}
