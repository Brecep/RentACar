package kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.rentAcar.business.abstracts.ColorService;
import kodlamaio.rentAcar.business.request.colors.CreateColorRequest;
import kodlamaio.rentAcar.business.request.colors.DeleteColorRequest;
import kodlamaio.rentAcar.business.request.colors.UpdateColorRequest;
import kodlamaio.rentAcar.business.response.colors.GetAllColorsResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.entities.conretes.Color;


@RestController
@RequestMapping("/api/colors")
public class ColorController {
	private ColorService colorService;

	public ColorController(ColorService colorService) {
		this.colorService = colorService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateColorRequest createColorRequest) {
		return this.colorService.add(createColorRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteColorRequest deleteColorRequest) {
		return colorService.delete(deleteColorRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateColorRequest updateColorRequest) {
		return colorService.update(updateColorRequest);
	}
	
	@GetMapping("/getall")  // endpoint
	public DataResult<List<GetAllColorsResponse>>  getAll() {
		return this.colorService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<Color>  GetById(int id) {
		return colorService.getById(id);
	}
	
	
	
}
