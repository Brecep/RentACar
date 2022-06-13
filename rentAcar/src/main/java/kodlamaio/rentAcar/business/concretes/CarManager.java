package kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.rentAcar.business.abstracts.CarService;
import kodlamaio.rentAcar.business.request.cars.CreateCarRequest;
import kodlamaio.rentAcar.business.request.cars.DeleteCarRequest;
import kodlamaio.rentAcar.business.request.cars.UpdateCarRequest;
import kodlamaio.rentAcar.business.response.cars.GetAllCarsResponse;
import kodlamaio.rentAcar.business.response.cars.GetByIdCarResponse;
import kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.ErrorResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import kodlamaio.rentAcar.dataAccess.abstracts.CarRepository;
import kodlamaio.rentAcar.entities.conretes.Car;

@Service
public class CarManager implements CarService {
	@Autowired
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;

	public CarManager(CarRepository carRepository, ModelMapperService modelMapperService) {
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		ifCheckExistCount(createCarRequest.getBrandId());
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		car.setState(1);
		this.carRepository.save(car);
		return new SuccessResult("CAR.ADDED");

	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		Car car = carRepository.findById(deleteCarRequest.getId());
		if (car.getState() == 1) {
			carRepository.deleteById(deleteCarRequest.getId());
			return new SuccessResult("CAR.DELETE");
		} else {
			return new ErrorResult("COULD.NOT.DELETE!!!");
		}

	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		car.setState(1);
		carRepository.save(car);
		return new SuccessResult("CAR.UPDATE");
	}

	@Override
	public DataResult<List<GetAllCarsResponse>> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarsResponse> response = cars.stream()
				.map(car -> this.modelMapperService.forResponse().map(car, GetAllCarsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCarsResponse>>(response);
	}

	@Override
	public DataResult<GetByIdCarResponse> getById(int id) {
		Car car = this.carRepository.findById(id);
		GetByIdCarResponse response = this.modelMapperService.forResponse().map(car, GetByIdCarResponse.class);
		return new SuccessDataResult<GetByIdCarResponse>(response, "CAR.GET.ID");
	}

	private void ifCheckExistCount(int id) {
		List<Car> cars = carRepository.getByBrandId(id);
		if (cars.size() > 4) {
			throw new BusinessException("CAR.EXIST");
		}
	}

}
