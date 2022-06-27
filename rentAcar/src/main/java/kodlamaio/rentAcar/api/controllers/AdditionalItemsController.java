
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

import kodlamaio.rentAcar.business.abstracts.AdditionalItemService;
import kodlamaio.rentAcar.business.request.additionalItems.CreateAdditionalItemRequest;
import kodlamaio.rentAcar.business.request.additionalItems.DeleteAdditionalItemRequest;
import kodlamaio.rentAcar.business.request.additionalItems.UpdateAdditionalItemRequest;
import kodlamaio.rentAcar.business.response.additionalItems.GetAdditionalItemResponse;
import kodlamaio.rentAcar.business.response.additionalItems.GetAllAdditionalItemsResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/additionalitems")
public class AdditionalItemsController {
	@Autowired
	private AdditionalItemService additionalItemService;

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateAdditionalItemRequest createAdditionalItemRequest) {
		return this.additionalItemService.add(createAdditionalItemRequest);
	}

	@PostMapping("/update")
	public Result update(@RequestBody @Valid UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		return this.additionalItemService.update(updateAdditionalItemRequest);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
		return this.additionalItemService.delete(deleteAdditionalItemRequest);
	}

	@GetMapping("/getall")
	public DataResult<List<GetAllAdditionalItemsResponse>> getAll() {
		return this.additionalItemService.getAll();
	}

	@GetMapping("/getbyid")
	public DataResult<GetAdditionalItemResponse> getById(@RequestParam @Valid int id) {
		return this.additionalItemService.getById(id);
	}
}
