package kodlamaio.rentAcar.api.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.rentAcar.business.abstracts.UserService;
import kodlamaio.rentAcar.business.request.users.CreateUserRequest;
import kodlamaio.rentAcar.business.request.users.DeleteUserRequest;
import kodlamaio.rentAcar.business.request.users.UpdateUserRequest;
import kodlamaio.rentAcar.core.utilities.result.Result;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	private UserService userService;

	public UsersController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateUserRequest createUserRequest) {
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
}
