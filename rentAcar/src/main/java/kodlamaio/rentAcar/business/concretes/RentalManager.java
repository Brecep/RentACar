package kodlamaio.rentAcar.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.rentAcar.business.abstracts.RentalService;
import kodlamaio.rentAcar.business.request.rentals.CreateRentalRequest;
import kodlamaio.rentAcar.business.request.rentals.DeleteRentalRequest;
import kodlamaio.rentAcar.business.request.rentals.UpdateRentalRequest;
import kodlamaio.rentAcar.business.response.rentals.GetAllRentalsResponse;
import kodlamaio.rentAcar.business.response.rentals.GetRentalResponse;
import kodlamaio.rentAcar.core.utilities.adapters.abstracts.FindexService;
import kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import kodlamaio.rentAcar.dataAccess.abstracts.CarRepository;
import kodlamaio.rentAcar.dataAccess.abstracts.RentalRepository;
import kodlamaio.rentAcar.dataAccess.abstracts.UserRepository;
import kodlamaio.rentAcar.entities.conretes.Car;
import kodlamaio.rentAcar.entities.conretes.Rental;
import kodlamaio.rentAcar.entities.conretes.User;

@Service
public class RentalManager implements RentalService {
	@Autowired
	private RentalRepository rentalRepository;
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private ModelMapperService modelMapperService;
	@Autowired
	private FindexService findexService;
	@Autowired
	private UserRepository userRepository;

	public RentalManager(RentalRepository rentalRepository, CarRepository carRepository,
			ModelMapperService modelMapperService) {
		this.rentalRepository = rentalRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		checkIfState(createRentalRequest.getCarId());
		User user = this.userRepository.getById(createRentalRequest.getUserId());
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);

		Date pickDate = createRentalRequest.getPickupDate();
		Date returnDate = createRentalRequest.getReturnDate();

		long totalDays = dayDifference(pickDate, returnDate);
		rental.setTotalDays(totalDays);

		Car car = carRepository.getById(createRentalRequest.getCarId());
		car.setId(createRentalRequest.getCarId());
		car.setState(3);
		rental.setCar(car);

		double totalPrice = car.getDailyPrice();
		int pickCity = createRentalRequest.getPickUpCityId();
		int returnCity = createRentalRequest.getReturnCityId();

		rental.setTotalPrice(fullPrice(totalDays, totalPrice, pickCity, returnCity));

		if (checkFindexScore(car.getMinFindex(), user.toString())) {
			rentalRepository.save(rental);
			return new SuccessResult("RENTAL.ADDED");
		} else {
			throw new BusinessException("NOT.ENOUGH.FİNDEX.SCORE");
		}
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		Car car = carRepository.findById(deleteRentalRequest.getCarId());
		Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
		rental.setCar(car);
		car.setState(1);

		rentalRepository.deleteById(deleteRentalRequest.getId());
		return new SuccessResult("RENTAL.DELETED");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {

		checkIfState(updateRentalRequest.getCarId());
		stateCar(updateRentalRequest.getCarId());
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);

		Date pickDate = updateRentalRequest.getPickupDate();
		Date returnDate = updateRentalRequest.getReturnDate();

		long totalDays = dayDifference(pickDate, returnDate);
		rental.setTotalDays(totalDays);

		Car car = carRepository.findById(updateRentalRequest.getCarId());
		car.setState(3);
		rental.setCar(car);

		int pickCity = updateRentalRequest.getPickUpCityId();
		int returnCity = updateRentalRequest.getReturnCityId();

		double totalPrice = car.getDailyPrice();
		rental.setTotalPrice(fullPrice(totalDays, totalPrice, pickCity, returnCity));

		rentalRepository.save(rental);

		return new SuccessResult("RENTAL.UPDATED");

	}

	/*
	 * private Result stateCar(CreateRentalRequest createRentalRequest) { Car car =
	 * carRepository.findById(createRentalRequest.getCarId());
	 * 
	 * for (Rental item : rentalRepository.findAll()) { if(item.getCar()!=car) {
	 * car.setState(1); } }
	 * 
	 * return new SuccessResult("UPDATE.RENTAL.STATE.UPDATE2"); }
	 */
	private long dayDifference(Date datePickup, Date dateReturned) {
		long dif = ((dateReturned.getTime() - datePickup.getTime()) / (1000 * 60 * 60 * 24));
		return dif;
	}

	private void checkIfState(int id) {
		Car car = carRepository.findById(id);
		if (car.getState() == 2)
			throw new BusinessException("STATE.NOT.FİX");
	}

	private void stateCar(int id) {
		Rental rental = rentalRepository.findById(id);
		Car car = rental.getCar();
		car.setState(1);
	}

	private double fullPrice(long day, double price, int pickId, int returnId) {
		if (pickId == returnId) {
			double fullprice = day * price;
			return fullprice;
		} else {
			double fullprice = (day * price) + 750;
			return fullprice;
		}

	}

	@Override
	public DataResult<List<GetAllRentalsResponse>> getAll() {
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalsResponse> response = rentals.stream()
				.map(rental -> this.modelMapperService.forResponse().map(rental, GetAllRentalsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllRentalsResponse>>(response);
	}

	@Override
	public DataResult<GetRentalResponse> getById(int id) {
		Rental rental = this.rentalRepository.findById(id);
		GetRentalResponse response = this.modelMapperService.forResponse().map(rental, GetRentalResponse.class);
		return new SuccessDataResult<GetRentalResponse>(response, "GET.BY.ID.RENTAL");
	}

	private boolean checkFindexScore(int findexScore, String tcNo) {
		boolean state = false;
		if (findexService.findexScore(tcNo) > findexScore)
			state = true;
		return state;
	}

}
