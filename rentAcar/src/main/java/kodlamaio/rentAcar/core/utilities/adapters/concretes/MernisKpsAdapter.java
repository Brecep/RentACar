package kodlamaio.rentAcar.core.utilities.adapters.concretes;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import kodlamaio.rentAcar.business.request.individuals.CreateIndividualCustomerRequest;
import kodlamaio.rentAcar.core.utilities.adapters.abstracts.PersonCheckService;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisKpsAdapter implements PersonCheckService {

	@Override
	public boolean checkPerson(CreateIndividualCustomerRequest user) throws NumberFormatException, RemoteException {

		KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();
		boolean result = kpsPublicSoapProxy.TCKimlikNoDogrula(Long.parseLong(user.getNationality()),
				user.getFirstName().toUpperCase(), user.getLastName().toUpperCase(), user.getBirthDate().getYear());
		return result;
	}
}