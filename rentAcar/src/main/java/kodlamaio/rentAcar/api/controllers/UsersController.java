package kodlamaio.rentAcar.api.controllers;

import java.rmi.RemoteException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.rentAcar.business.abstracts.UserService;
import kodlamaio.rentAcar.business.request.users.CreateUserRequest;
import kodlamaio.rentAcar.business.request.users.DeleteUserRequest;
import kodlamaio.rentAcar.business.request.users.UpdateUserRequest;
import kodlamaio.rentAcar.business.response.users.GetAllUsersResponse;
import kodlamaio.rentAcar.business.response.users.GetUserResponse;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	private UserService userService;

	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateUserRequest createUserRequest)
			throws NumberFormatException, RemoteException {
		return this.userService.add(createUserRequest);
	}

	@PostMapping("/delete")
	public Result delete(DeleteUserRequest deleteUserRequest) {
		return this.userService.delete(deleteUserRequest);
	}

	@PostMapping("/update")
	public Result update(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
		return this.userService.update(updateUserRequest);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetAllUsersResponse>> getAll() {
		return userService.getAll();
	}

	@GetMapping("/getAllByPage")
	public DataResult<List<GetAllUsersResponse>> getAll(@RequestParam int pageNo, int pageSize) {
		return userService.getAll(pageNo, pageSize);
	}

	@GetMapping("/getById")
	public DataResult<GetUserResponse> getById(@RequestParam int id) {
		return this.userService.getById(id);
	}
}
