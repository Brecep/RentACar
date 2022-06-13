package kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import kodlamaio.rentAcar.business.request.brands.CreateBrandRequest;
import kodlamaio.rentAcar.business.request.brands.DeleteBrandRequest;
import kodlamaio.rentAcar.business.request.brands.UpdateBrandRequest;
import kodlamaio.rentAcar.business.response.brands.GetAllBrandsResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.entities.conretes.Brand;

public interface BrandService {
	Result add(CreateBrandRequest createBrandRequest);

	Result delete(DeleteBrandRequest deleteBrandRequest);

	Result update(UpdateBrandRequest updateBrandRequest);

	DataResult<List<GetAllBrandsResponse>> getAll();

	DataResult<Brand> getById(int id);

}
