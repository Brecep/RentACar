package kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import kodlamaio.rentAcar.business.request.maintenances.CreateMaintenanceRequest;
import kodlamaio.rentAcar.business.request.maintenances.DeleteMaintenanceRequest;
import kodlamaio.rentAcar.business.request.maintenances.UpdateMaintenenceRequest;
import kodlamaio.rentAcar.business.response.maintenances.GetAllMaintenancesResponse;
import kodlamaio.rentAcar.business.response.maintenances.GetMaintenanceResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

public interface MaintenanceService {
	Result add(CreateMaintenanceRequest createMaintenanceRequest);

	Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest);

	Result update(UpdateMaintenenceRequest updateMaintenenceRequest);

	Result updateState(UpdateMaintenenceRequest updateMaintenenceRequest);

	DataResult<GetMaintenanceResponse> getById(int id);

	DataResult<List<GetAllMaintenancesResponse>> getAll();
}
