package kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;

import kodlamaio.rentAcar.business.abstracts.ColorService;
import kodlamaio.rentAcar.business.request.colors.CreateColorRequest;
import kodlamaio.rentAcar.business.request.colors.DeleteColorRequest;
import kodlamaio.rentAcar.business.request.colors.UpdateColorRequest;
import kodlamaio.rentAcar.business.response.colors.GetAllColorsResponse;
import kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import kodlamaio.rentAcar.dataAccess.abstracts.ColorRepository;
import kodlamaio.rentAcar.entities.conretes.Color;

@Service
public class ColorManager implements ColorService{
	@Autowired
	private ColorRepository colorRepository;
	private ModelMapperService modelMapperService;
	
	
	public ColorManager(ColorRepository colorRepository, ModelMapperService modelMapperService) {
		this.colorRepository = colorRepository;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public Result add(CreateColorRequest createColorRequest) {
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		//color.setName(createColorRequest.getName());
		this.colorRepository.save(color);
		return new SuccessResult("COLOR.ADDED");
	}
	
	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		colorRepository.deleteById(deleteColorRequest.getId());
		return new SuccessResult("COLOR.DELETE");
	}
	
	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		//colorRepository.findById(updateColorRequest.getId());
		//color.setName(updateColorRequest.getName());
		colorRepository.save(color);
		return new SuccessResult("COLOR.UPDATE");
	}
	
	@Override
	public DataResult<List<GetAllColorsResponse>>  getAll() {
		List<Color> colors = colorRepository.findAll();
		List<GetAllColorsResponse> response = colors.stream().map(color -> this.modelMapperService.forResponse()
				.map(color, GetAllColorsResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllColorsResponse>>(response) ;
	}
	
	@Override
	public DataResult<Color>  getById(int id) {
		
		return new SuccessDataResult<Color>(colorRepository.findById(id),"COLOR_ID.GET");
	}

}
