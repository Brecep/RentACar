package kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.rentAcar.business.abstracts.RentalService;
import kodlamaio.rentAcar.business.request.rentals.CreateRentalRequest;
import kodlamaio.rentAcar.business.request.rentals.DeleteRentalRequest;
import kodlamaio.rentAcar.business.request.rentals.UpdateRentalRequest;
import kodlamaio.rentAcar.business.response.rentals.GetAllRentalsResponse;
import kodlamaio.rentAcar.business.response.rentals.GetRentalResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
	private RentalService rentalService;

	public RentalsController(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateRentalRequest createRentalRequest) {
		return rentalService.add(createRentalRequest);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteRentalRequest deleteRentalRequest) {
		return rentalService.delete(deleteRentalRequest);
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateRentalRequest updateRentalRequest) {
		return rentalService.update(updateRentalRequest);
	}

	@GetMapping("/getall")
	public DataResult<List<GetAllRentalsResponse>> getAll(GetAllRentalsResponse getAllRentalsResponse) {
		return rentalService.getAll();
	}

	@GetMapping("/getbyid")
	public DataResult<GetRentalResponse> getById(int id) {
		return rentalService.getById(id);
	}

}
