package kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.rentAcar.business.abstracts.BrandService;
import kodlamaio.rentAcar.business.request.brands.CreateBrandRequest;
import kodlamaio.rentAcar.business.request.brands.DeleteBrandRequest;
import kodlamaio.rentAcar.business.request.brands.UpdateBrandRequest;
import kodlamaio.rentAcar.business.response.brands.GetAllBrandsResponse;
import kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import kodlamaio.rentAcar.dataAccess.abstracts.BrandRepository;
import kodlamaio.rentAcar.entities.conretes.Brand;

//brandServiceImpl
@Service
public class BrandManager implements BrandService {
	@Autowired
	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;

	public BrandManager(BrandRepository brandRepository, ModelMapperService modelMapperService) {
		this.brandRepository = brandRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		checkIfBrandExistByName(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandRepository.save(brand);

		return new SuccessResult("BRAND.ADDED");
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		brandRepository.deleteById(deleteBrandRequest.getId());
		return new SuccessResult("BRAND.DELETE");
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {

		Brand brandToUpdate = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		brandRepository.save(brandToUpdate);
		return new SuccessResult("BRAND.UPDATE");
	}

	private void checkIfBrandExistByName(String name) {
		Brand currentBrand = this.brandRepository.findByName(name);
		if (currentBrand != null) {
			throw new BusinessException("BRAND.EXİST");
		}
	}

	@Override
	public DataResult<List<GetAllBrandsResponse>> getAll() {
		List<Brand> brands = this.brandRepository.findAll();
		List<GetAllBrandsResponse> response = brands.stream()
				.map(brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllBrandsResponse>>(response);
	}

	@Override
	public DataResult<Brand> getById(int id) {
		return new SuccessDataResult<Brand>(this.brandRepository.findById(id));
	}

}
