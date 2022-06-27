package kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.rentAcar.business.abstracts.AdressService;
import kodlamaio.rentAcar.business.request.adresses.CreateAdressRequest;
import kodlamaio.rentAcar.business.request.adresses.DeleteAdressRequest;
import kodlamaio.rentAcar.business.response.adresses.GetAllAdressResponse;
import kodlamaio.rentAcar.business.response.adresses.GetByAdressResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/adresses")
public class AdressesController {
	private AdressService adressService;

	public AdressesController(AdressService adressService) {
		super();
		this.adressService = adressService;
	}

	@PostMapping("add")
	public Result add(@RequestBody CreateAdressRequest createAddressRequest) {
		return adressService.add(createAddressRequest);
	}

	@PostMapping("delete")
	public Result delete(DeleteAdressRequest deleteAddressRequest) {
		return adressService.delete(deleteAddressRequest);
	}

	@GetMapping("getall")
	public DataResult<List<GetAllAdressResponse>> getAll() {
		return adressService.getAll();
	}

	@GetMapping("getById")
	public DataResult<GetByAdressResponse> getById(int id) {
		return adressService.getById(id);
	}
}
