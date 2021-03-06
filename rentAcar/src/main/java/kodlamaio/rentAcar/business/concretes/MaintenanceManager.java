package kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.rentAcar.business.abstracts.MaintenanceService;
import kodlamaio.rentAcar.business.request.maintenances.CreateMaintenanceRequest;
import kodlamaio.rentAcar.business.request.maintenances.DeleteMaintenanceRequest;
import kodlamaio.rentAcar.business.request.maintenances.UpdateMaintenenceRequest;
import kodlamaio.rentAcar.business.response.maintenances.GetAllMaintenancesResponse;
import kodlamaio.rentAcar.business.response.maintenances.GetMaintenanceResponse;
import kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import kodlamaio.rentAcar.dataAccess.abstracts.CarRepository;
import kodlamaio.rentAcar.dataAccess.abstracts.MaintenanceRepository;
import kodlamaio.rentAcar.entities.conretes.Car;
import kodlamaio.rentAcar.entities.conretes.Maintenance;


@Service
public class MaintenanceManager implements MaintenanceService{
	@Autowired
	private MaintenanceRepository maintenanceRepository;
	private ModelMapperService modelMapperService;
	private CarRepository carRepository;

	public MaintenanceManager(MaintenanceRepository maintenanceRepository, CarRepository carRepository, ModelMapperService modelMapperService) {
		this.maintenanceRepository = maintenanceRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {
		Maintenance maintenance = this.modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);
//		maintenance.setDateSent(createMaintenanceRequest.getDateSent());
//		maintenance.setDateReturned(createMaintenanceRequest.getDateReturned());
		
		Car car = carRepository.findById(createMaintenanceRequest.getCarId());
//		car.setId(createMaintenanceRequest.getCarId());
		car.setState(2);
		maintenance.setCar(car);
		
		maintenanceRepository.save(maintenance);
		return new SuccessResult("MAINTENANCE.ADDED");
	}
	
	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		maintenanceRepository.deleteById(deleteMaintenanceRequest.getId());
		return new SuccessResult("MAINTENANCE.DELETED");
	}

	@Override
	public Result update(UpdateMaintenenceRequest updateMaintenenceRequest) {
		
		Maintenance maintenance = this.modelMapperService.forRequest().map(updateMaintenenceRequest, Maintenance.class);

		maintenanceRepository.save(maintenance);
		
		return new SuccessResult("MAINTENANCE.UPDATE");
	}

	@Override
	public Result updateState(UpdateMaintenenceRequest updateMaintenenceRequest) {
		Car car = carRepository.findById(updateMaintenenceRequest.getCarId());
		
		if(car.getState()==1) {
			car.setState(2);
		}else {
			car.setState(1);
		}
		carRepository.save(car);
		return new SuccessResult("STATE.UPDATE");
	}

	@Override
	public DataResult<GetMaintenanceResponse> getById(int id) {
		
		Maintenance maintenance = maintenanceRepository.findById(id);
		GetMaintenanceResponse response = this.modelMapperService.forResponse().map(maintenance, GetMaintenanceResponse.class);
		return new SuccessDataResult<GetMaintenanceResponse>(response,"GET_BY_ID");
	}

	@Override
	public DataResult<List<GetAllMaintenancesResponse>> getAll() {
		List<Maintenance> maintenances = this.maintenanceRepository.findAll();
		List<GetAllMaintenancesResponse> response = maintenances.stream().map(maintenance -> this.modelMapperService.forResponse()
				.map(maintenance, GetAllMaintenancesResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllMaintenancesResponse>>(response,"MAINTENACES.LISTED");
	}



}
