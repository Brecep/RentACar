package kodlamaio.rentAcar.core.utilities.adapters.abstracts;

import java.rmi.RemoteException;

import kodlamaio.rentAcar.business.request.users.CreateUserRequest;

public interface PersonCheckService {
	boolean checkPerson(CreateUserRequest user) throws NumberFormatException, RemoteException;
}