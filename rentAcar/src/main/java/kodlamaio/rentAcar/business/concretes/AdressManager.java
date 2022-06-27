package kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.rentAcar.business.abstracts.AdressService;
import kodlamaio.rentAcar.business.request.adresses.CreateAdressRequest;
import kodlamaio.rentAcar.business.request.adresses.DeleteAdressRequest;
import kodlamaio.rentAcar.business.request.adresses.UpdateAdressRequest;
import kodlamaio.rentAcar.business.response.adresses.GetAllAdressResponse;
import kodlamaio.rentAcar.business.response.adresses.GetByAdressResponse;
import kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import kodlamaio.rentAcar.dataAccess.abstracts.AdressRepository;
import kodlamaio.rentAcar.dataAccess.abstracts.UserRepository;
import kodlamaio.rentAcar.entities.conretes.Adress;
import kodlamaio.rentAcar.entities.conretes.User;

@Service
public class AdressManager implements AdressService {
	@Autowired
	private AdressRepository adressRepository;
	@Autowired
	private ModelMapperService modelMapperService;
	private UserRepository userRepository;

	public AdressManager(AdressRepository adressRepository, ModelMapperService modelMapperService) {
		super();
		this.adressRepository = adressRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateAdressRequest createAdressRequest) {
		checkIfUser(createAdressRequest.getUserId());
		Adress adress = this.modelMapperService.forRequest().map(createAdressRequest, Adress.class);
		this.adressRepository.save(adress);
		return new SuccessResult("ADRESS.ADDED");
	}

	@Override
	public Result delete(DeleteAdressRequest deleteAdressRequest) {
		this.adressRepository.deleteById(deleteAdressRequest.getId());
		return new SuccessResult("ADRESS.DELETED");
	}

	@Override
	public Result update(UpdateAdressRequest updateAdressRequest) {
		Adress adressToUpdate = this.modelMapperService.forRequest().map(updateAdressRequest, Adress.class);
		this.adressRepository.save(adressToUpdate);
		return new SuccessResult("ADRESS.UPDATED");
	}

	@Override
	public DataResult<List<GetAllAdressResponse>> getAll() {
		List<Adress> adresses = this.adressRepository.findAll();
		List<GetAllAdressResponse> responses = adresses.stream()
				.map(adress -> this.modelMapperService.forResponse().map(adress, GetAllAdressResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAdressResponse>>(responses);
	}

	@Override
	public DataResult<GetByAdressResponse> getById(int id) {
		Adress adress = this.adressRepository.getById(id);
		GetByAdressResponse response = modelMapperService.forResponse().map(adress, GetByAdressResponse.class);
		return new SuccessDataResult<GetByAdressResponse>(response);
	}

	private void checkIfUser(int id) {
		User user = userRepository.getById(id);
		if (user == null) {
			throw new BusinessException("USER.NOT.FOUND!!!");
		}
	}

}
