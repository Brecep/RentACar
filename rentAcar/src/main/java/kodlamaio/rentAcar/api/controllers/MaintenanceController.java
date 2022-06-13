package kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.rentAcar.business.abstracts.MaintenanceService;
import kodlamaio.rentAcar.business.request.maintenances.CreateMaintenanceRequest;
import kodlamaio.rentAcar.business.request.maintenances.DeleteMaintenanceRequest;
import kodlamaio.rentAcar.business.request.maintenances.UpdateMaintenenceRequest;
import kodlamaio.rentAcar.business.response.maintenances.GetAllMaintenancesResponse;
import kodlamaio.rentAcar.business.response.maintenances.GetMaintenanceResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {
	private MaintenanceService maintenanceService;

	public MaintenanceController(MaintenanceService maintenanceService) {
		this.maintenanceService = maintenanceService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateMaintenanceRequest createMaintenanceRequest) {
		return maintenanceService.add(createMaintenanceRequest);
	}

	@PostMapping("/delete")
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		return maintenanceService.delete(deleteMaintenanceRequest);
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateMaintenenceRequest updateMaintenenceRequest) {
		return maintenanceService.update(updateMaintenenceRequest);
	}

	@PostMapping("/updateState")
	public Result updateState(@RequestBody UpdateMaintenenceRequest udateMaintenenceRequest) {
		return maintenanceService.updateState(udateMaintenenceRequest);
	}

	@GetMapping("/getall")
	public DataResult<List<GetAllMaintenancesResponse>> getAll() {
		return maintenanceService.getAll();
	}

	@GetMapping("getById")
	public DataResult<GetMaintenanceResponse> getById(GetMaintenanceResponse getMaintenanceResponse) {
		return maintenanceService.getById(getMaintenanceResponse.getCarId());
	}

}
