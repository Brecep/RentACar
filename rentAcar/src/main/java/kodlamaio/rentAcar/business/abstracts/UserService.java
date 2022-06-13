package kodlamaio.rentAcar.business.abstracts;

import kodlamaio.rentAcar.business.request.users.CreateUserRequest;
import kodlamaio.rentAcar.business.request.users.DeleteUserRequest;
import kodlamaio.rentAcar.business.request.users.UpdateUserRequest;
import kodlamaio.rentAcar.core.utilities.result.Result;

public interface UserService {
	Result add(CreateUserRequest createUserRequest);
	Result delete(DeleteUserRequest deleteUserRequest);
	Result update(UpdateUserRequest updateUserRequest);
	//List<List<GetAllUsersResponse>> getAll();
}
