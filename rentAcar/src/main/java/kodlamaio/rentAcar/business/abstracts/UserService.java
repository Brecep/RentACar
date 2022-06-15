package kodlamaio.rentAcar.business.abstracts;

import java.rmi.RemoteException;
import java.util.List;

import kodlamaio.rentAcar.business.request.users.CreateUserRequest;
import kodlamaio.rentAcar.business.request.users.DeleteUserRequest;
import kodlamaio.rentAcar.business.request.users.UpdateUserRequest;
import kodlamaio.rentAcar.business.response.users.GetAllUsersResponse;
import kodlamaio.rentAcar.business.response.users.GetUserResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.entities.conretes.User;

public interface UserService {
	Result add(CreateUserRequest createUserRequest) throws NumberFormatException, RemoteException;

	Result delete(DeleteUserRequest deleteUserRequest);

	Result update(UpdateUserRequest updateUserRequest);

	DataResult<List<GetAllUsersResponse>> getAll();

	DataResult<GetUserResponse> getById(int id);

	DataResult<List<GetAllUsersResponse>> getAll(Integer pageNo, Integer pageSize);
}
