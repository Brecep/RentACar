package kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import kodlamaio.rentAcar.business.request.additionalItems.CreateAdditionalItemRequest;
import kodlamaio.rentAcar.business.request.additionalItems.DeleteAdditionalItemRequest;
import kodlamaio.rentAcar.business.request.additionalItems.UpdateAdditionalItemRequest;
import kodlamaio.rentAcar.business.response.additionalItems.GetAdditionalItemResponse;
import kodlamaio.rentAcar.business.response.additionalItems.GetAllAdditionalItemsResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

public interface AdditionalItemService {
	Result add(CreateAdditionalItemRequest createAdditionalItemRequest);

	Result update(UpdateAdditionalItemRequest updateAdditionalItemRequest);

	Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest);

	DataResult<List<GetAllAdditionalItemsResponse>> getAll();

	DataResult<GetAdditionalItemResponse> getById(int id);
}
