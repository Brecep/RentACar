package kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.rentAcar.business.abstracts.CorporateCustomerService;
import kodlamaio.rentAcar.business.request.corporate.CreateCorporateCustomerRequest;
import kodlamaio.rentAcar.business.request.corporate.DeleteCorporateCustomerRequest;
import kodlamaio.rentAcar.business.request.corporate.UpdateCorporateCustomerRequest;
import kodlamaio.rentAcar.business.response.corporates.GetAllCorporateCustomerResponse;
import kodlamaio.rentAcar.business.response.corporates.GetCorporateCustomerResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/coroparates")
public class CorporateController {
	private CorporateCustomerService corporateCustomerService;

	public CorporateController(CorporateCustomerService corporateCustomerService) {
		super();
		this.corporateCustomerService = corporateCustomerService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateCorporateCustomerRequest corporateCustomerRequest) {
		return this.corporateCustomerService.add(corporateCustomerRequest);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteCorporateCustomerRequest corporateCustomerRequest) {
		return this.corporateCustomerService.delete(corporateCustomerRequest);
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateCorporateCustomerRequest corporateCustomerRequest) {
		return this.corporateCustomerService.update(corporateCustomerRequest);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetAllCorporateCustomerResponse>> getAll() {
		return corporateCustomerService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<GetCorporateCustomerResponse> getById(@RequestParam int id) {
		return this.corporateCustomerService.getById(id);
	}

}
