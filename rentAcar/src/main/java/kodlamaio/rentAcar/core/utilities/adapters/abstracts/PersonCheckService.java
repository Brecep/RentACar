package kodlamaio.rentAcar.core.utilities.adapters.abstracts;

import java.rmi.RemoteException;

import kodlamaio.rentAcar.business.request.individuals.CreateIndividualCustomerRequest;

public interface PersonCheckService {
	boolean checkPerson(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NumberFormatException, RemoteException;

	//boolean checkPerson1(CreateIndividualCustomerRequest createIndividualRequest);
}