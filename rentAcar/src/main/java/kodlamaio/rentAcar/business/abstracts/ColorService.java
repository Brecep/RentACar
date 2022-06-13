package kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import kodlamaio.rentAcar.business.request.colors.CreateColorRequest;
import kodlamaio.rentAcar.business.request.colors.DeleteColorRequest;
import kodlamaio.rentAcar.business.request.colors.UpdateColorRequest;
import kodlamaio.rentAcar.business.response.colors.GetAllColorsResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.entities.conretes.Color;

public interface ColorService {
	Result add(CreateColorRequest createColorRequest);

	Result delete(DeleteColorRequest deleteColorRequest);

	Result update(UpdateColorRequest updateColorRequest);

	DataResult<List<GetAllColorsResponse>> getAll();

	DataResult<Color> getById(int id);
}
