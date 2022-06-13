package kodlamaio.rentAcar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.rentAcar.business.abstracts.UserService;
import kodlamaio.rentAcar.business.request.users.CreateUserRequest;
import kodlamaio.rentAcar.business.request.users.DeleteUserRequest;
import kodlamaio.rentAcar.business.request.users.UpdateUserRequest;
import kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import kodlamaio.rentAcar.dataAccess.abstracts.UserRespository;
import kodlamaio.rentAcar.entities.conretes.User;


@Service
public class UserManager implements UserService{
	@Autowired
	private UserRespository userRespository;
	private ModelMapperService modelMapperService;
	
	
	public UserManager(UserRespository userRespository, ModelMapperService modelMapperService) {
		this.userRespository = userRespository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateUserRequest createUserRequest) {
		User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
		this.userRespository.save(user);
		return new SuccessResult("USER.ADDED");
	}

	@Override
	public Result delete(DeleteUserRequest deleteUserRequest) {
		userRespository.deleteById(deleteUserRequest.getId());
		return new SuccessResult("USER.DELETED");
	}

	@Override
	public Result update(UpdateUserRequest updateUserRequest) {
		User user = this.modelMapperService.forRequest().map(updateUserRequest, User.class);
		this.userRespository.save(user);
		return new SuccessResult("USER.UPDATE");
	}
	
//	private void notRepeatTcNo(String tcNo) {
//		for
//	}

}
