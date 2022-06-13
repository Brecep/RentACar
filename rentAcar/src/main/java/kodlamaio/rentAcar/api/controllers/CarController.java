package kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.rentAcar.business.abstracts.CarService;
import kodlamaio.rentAcar.business.request.cars.CreateCarRequest;
import kodlamaio.rentAcar.business.request.cars.DeleteCarRequest;
import kodlamaio.rentAcar.business.request.cars.UpdateCarRequest;
import kodlamaio.rentAcar.business.response.cars.GetAllCarsResponse;
import kodlamaio.rentAcar.business.response.cars.GetByIdCarResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/cars")
public class CarController {
	
	private CarService carService;
	
	public CarController(CarService carService) {
		this.carService = carService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateCarRequest createcarRequest) {
		return this.carService.add(createcarRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteCarRequest deleteCarRequest) {
		return carService.delete(deleteCarRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateCarRequest updateCarRequest) {
		return carService.update(updateCarRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<GetAllCarsResponse>>  getAll(){ 
		return carService.getAll();
	}
	
	@PostMapping("/getbyid")
	public DataResult<GetByIdCarResponse>  getById(int id) {
		return carService.getById(id);
	}
}
