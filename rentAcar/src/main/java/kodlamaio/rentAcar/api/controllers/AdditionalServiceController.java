package kodlamaio.rentAcar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.rentAcar.business.abstracts.AdditionalService;
import kodlamaio.rentAcar.business.request.additionalservices.CreateAdditionalServiceRequest;
import kodlamaio.rentAcar.business.request.additionalservices.DeleteAdditionalServiceRequest;
import kodlamaio.rentAcar.business.request.additionalservices.UpdateAdditionalServiceRequest;
import kodlamaio.rentAcar.business.response.additionalservices.GetAdditionalServiceResponse;
import kodlamaio.rentAcar.business.response.additionalservices.GetAllAdditionalServiceResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/additionals")
public class AdditionalServiceController {

	@Autowired
	private AdditionalService additionalService;

	public AdditionalServiceController(AdditionalService additionalService) {
		this.additionalService = additionalService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalRequest) {

		return this.additionalService.add(createAdditionalRequest);
	}

	@PostMapping("/update")
	public Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalRequest) {
		return this.additionalService.update(updateAdditionalRequest);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteAdditionalServiceRequest deleteAdditionalRequest) {
		return this.additionalService.delete(deleteAdditionalRequest);
	}

	@GetMapping("/getall")
	public DataResult<List<GetAllAdditionalServiceResponse>> getAll() {
		return this.additionalService.getAll();
	}

	@GetMapping("/getbyid")
	public DataResult<GetAdditionalServiceResponse> getById(@RequestParam @Valid int id) {
		return this.additionalService.getById(id);
	}
}
