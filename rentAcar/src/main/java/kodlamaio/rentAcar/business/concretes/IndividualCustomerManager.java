package kodlamaio.rentAcar.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kodlamaio.rentAcar.business.abstracts.IndividualCustomerService;
import kodlamaio.rentAcar.business.request.individuals.CreateIndividualCustomerRequest;
import kodlamaio.rentAcar.business.request.individuals.DeleteIndividualCustomerRequest;
import kodlamaio.rentAcar.business.request.individuals.UpdateIndividualCustomerRequest;
import kodlamaio.rentAcar.business.response.individuals.GetAllIndividualCustomerResponse;
import kodlamaio.rentAcar.business.response.individuals.GetIndividualCustomerResponse;
import kodlamaio.rentAcar.core.utilities.adapters.abstracts.FindexService;
import kodlamaio.rentAcar.core.utilities.adapters.abstracts.PersonCheckService;
import kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import kodlamaio.rentAcar.dataAccess.abstracts.IndividualCustomerRepository;
import kodlamaio.rentAcar.entities.conretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

	private IndividualCustomerRepository individualCustomerRepository;
	private ModelMapperService mapperService;
	private PersonCheckService personCheckService;

	public IndividualCustomerManager(IndividualCustomerRepository individualCustomerRepository,
			ModelMapperService mapperService, PersonCheckService personCheckService, FindexService findeksService) {
		this.individualCustomerRepository = individualCustomerRepository;
		this.mapperService = mapperService;
		this.personCheckService = personCheckService;
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualRequest)
			throws NumberFormatException, RemoteException {
		checkUserNationalityFromRepository(createIndividualRequest.getNationality());
		checkIfUserExistsByNationalityFromMernis(createIndividualRequest);
		checkUserEmail(createIndividualRequest.getEmail());
		IndividualCustomer individualCustomer = this.mapperService.forRequest().map(createIndividualRequest,
				IndividualCustomer.class);
		this.individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("USER.ADDED");
	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualRequest) {
		checkIfUserExists(deleteIndividualRequest.getIndividualCustomerId());
		this.individualCustomerRepository.deleteById(deleteIndividualRequest.getIndividualCustomerId());
		return new SuccessResult("USER.DELETED");
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualRequest) {
		checkIfUserExists(updateIndividualRequest.getIndividualCustomerId());
		checkUserNationalityFromRepository(updateIndividualRequest.getNationality());
		checkUserUpdateEmail(updateIndividualRequest.getIndividualCustomerId(), updateIndividualRequest.getEmail());
		IndividualCustomer individualCustomer = this.mapperService.forRequest().map(updateIndividualRequest,
				IndividualCustomer.class);
		this.individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("USER.UPDATED");
	}

	@Override
	public DataResult<List<GetAllIndividualCustomerResponse>> getAll() {
		List<IndividualCustomer> users = this.individualCustomerRepository.findAll();

		List<GetAllIndividualCustomerResponse> response = users.stream()
				.map(user -> this.mapperService.forResponse().map(user, GetAllIndividualCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllIndividualCustomerResponse>>(response);
	}

	@Override
	public DataResult<GetIndividualCustomerResponse> getById(int id) {
		checkIfUserExists(id);
		IndividualCustomer user = this.individualCustomerRepository.findById(id).get();

		GetIndividualCustomerResponse response = this.mapperService.forResponse().map(user,
				GetIndividualCustomerResponse.class);
		return new SuccessDataResult<GetIndividualCustomerResponse>(response);
	}

	@Override
	public DataResult<List<GetAllIndividualCustomerResponse>> getAll(Integer pageNo, Integer pageSize) {
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
		List<IndividualCustomer> users = this.individualCustomerRepository.findAll(pageable).getContent();

		List<GetAllIndividualCustomerResponse> response = users.stream()
				.map(user -> this.mapperService.forResponse().map(user, GetAllIndividualCustomerResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllIndividualCustomerResponse>>(response);
	}

	private void checkIfUserExistsByNationalityFromMernis(CreateIndividualCustomerRequest createIndividualRequest)
			throws NumberFormatException, RemoteException {
		if (!personCheckService.checkPerson(createIndividualRequest)) {
			throw new BusinessException("USER.IS.NOT.EXISTS.MERNIS");
		}
	}

	private void checkUserNationalityFromRepository(String nationality) {
		IndividualCustomer user = this.individualCustomerRepository.findByNationality(nationality);
		if (user != null) {
			throw new BusinessException("USER.EXISTS.REPOSITORY");
		}
	}

	private void checkIfUserExists(int id) {
		IndividualCustomer user = this.individualCustomerRepository.findById(id).get();
		if (user == null) {
			throw new BusinessException("THERE.IS.NOT.USER");
		}
	}
	
	private void checkUserEmail(String email) {
		IndividualCustomer user = this.individualCustomerRepository.findByEmail(email);
		if (user != null) {
			throw new BusinessException("THIS.EMAIL.ALREADEY.EXISTS");
		}
	}
	
	private void checkUserUpdateEmail(int userId, String email) {
		IndividualCustomer user = this.individualCustomerRepository.findById(userId).get();
		
		if (user.getEmail() != email) {
			checkUserEmail(email);
		}
	}
}