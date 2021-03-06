package kodlamaio.rentAcar.api.controllers;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.rentAcar.business.abstracts.IndividualCustomerService;
import kodlamaio.rentAcar.business.request.individuals.CreateIndividualCustomerRequest;
import kodlamaio.rentAcar.business.request.individuals.DeleteIndividualCustomerRequest;
import kodlamaio.rentAcar.business.request.individuals.UpdateIndividualCustomerRequest;
import kodlamaio.rentAcar.business.response.individuals.GetAllIndividualCustomerResponse;
import kodlamaio.rentAcar.business.response.individuals.GetIndividualCustomerResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/individuals")
public class IndividualController {

	private IndividualCustomerService userService;

	public IndividualController(IndividualCustomerService userService) {
		this.userService = userService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateIndividualCustomerRequest createUserRequest)
			throws NumberFormatException, RemoteException {
		return this.userService.add(createUserRequest);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetAllIndividualCustomerResponse>> getAll() {
		return userService.getAll();
	}

	@GetMapping("/getAllByPage")
	public DataResult<List<GetAllIndividualCustomerResponse>> getAll(@RequestParam int pageNo, int pageSize) {
		return userService.getAll(pageNo, pageSize);
	}

	@GetMapping("/getById")
	public DataResult<GetIndividualCustomerResponse> getById(@RequestParam int id) {
		return this.userService.getById(id);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteIndividualCustomerRequest deleteUserRequest) {
		return this.userService.delete(deleteUserRequest);
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateIndividualCustomerRequest updateUserRequest) {
		return this.userService.update(updateUserRequest);
	}

}