package kodlamaio.rentAcar.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.rentAcar.business.abstracts.UserService;
import kodlamaio.rentAcar.business.request.users.CreateUserRequest;
import kodlamaio.rentAcar.business.request.users.DeleteUserRequest;
import kodlamaio.rentAcar.business.request.users.UpdateUserRequest;
import kodlamaio.rentAcar.business.response.users.GetAllUsersResponse;
import kodlamaio.rentAcar.business.response.users.GetUserResponse;
import kodlamaio.rentAcar.core.utilities.adapters.abstracts.PersonCheckService;
import kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import kodlamaio.rentAcar.dataAccess.abstracts.UserRepository;
import kodlamaio.rentAcar.entities.conretes.User;

@Service
public class UserManager implements UserService {
	@Autowired
	private UserRepository userRepository;
	private ModelMapperService modelMapperService;

	private PersonCheckService personCheckService;

	public UserManager(UserRepository userRespository, ModelMapperService modelMapperService) {
		this.userRepository = userRespository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateUserRequest createUserRequest) throws NumberFormatException, RemoteException {
		checkIfUserExistsByNationality(createUserRequest);
		User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
		this.userRepository.save(user);
		return new SuccessResult("USER.ADDED");
	}

	@Override
	public Result delete(DeleteUserRequest deleteUserRequest) {
		userRepository.deleteById(deleteUserRequest.getId());
		return new SuccessResult("USER.DELETED");
	}

	@Override
	public Result update(UpdateUserRequest updateUserRequest) {
		User user = this.modelMapperService.forRequest().map(updateUserRequest, User.class);
		this.userRepository.save(user);
		return new SuccessResult("USER.UPDATE");
	}

	@Override
	public DataResult<List<GetAllUsersResponse>> getAll() {
		List<User> users = this.userRepository.findAll();

		List<GetAllUsersResponse> response = users.stream()
				.map(user -> this.modelMapperService.forResponse().map(user, GetAllUsersResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllUsersResponse>>(response);
	}

	@Override
	public DataResult<GetUserResponse> getById(int id) {
		User user = this.userRepository.findById(id);

		GetUserResponse response = this.modelMapperService.forResponse().map(user, GetUserResponse.class);
		return new SuccessDataResult<GetUserResponse>(response);
	}

	@Override
	public DataResult<List<GetAllUsersResponse>> getAll(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	private void checkIfUserExistsByNationality(CreateUserRequest createUserRequest)
			throws NumberFormatException, RemoteException {
		if (!personCheckService.checkPerson(createUserRequest)) {
			throw new BusinessException("USER.NOT.FOUND");
		}
	}

//	private void notRepeatTcNo(String tcNo) {
//		for
//	}

}
