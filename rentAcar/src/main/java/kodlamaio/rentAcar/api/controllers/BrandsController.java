package kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.rentAcar.business.abstracts.BrandService;
import kodlamaio.rentAcar.business.request.brands.CreateBrandRequest;
import kodlamaio.rentAcar.business.request.brands.DeleteBrandRequest;
import kodlamaio.rentAcar.business.request.brands.UpdateBrandRequest;
import kodlamaio.rentAcar.business.response.brands.GetAllBrandsResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.entities.conretes.Brand;

//localhost:8080/api/brands/sayhello
@RestController
@RequestMapping("/api/brands")
public class BrandsController {
	
	private BrandService brandService;
	
	public BrandsController(BrandService brandService) {
		this.brandService = brandService;
	}

	@GetMapping("/getall")  // endpoint
	public DataResult<List<GetAllBrandsResponse>> getAll() {
		 
		return brandService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateBrandRequest createBrandRequest) {
		return this.brandService.add(createBrandRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		return brandService.delete(deleteBrandRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		return brandService.update(updateBrandRequest);
	}
	
	@GetMapping("/getbyid")
	public DataResult<Brand> GetById(int id) {
		return brandService.getById(id);
	}
}
