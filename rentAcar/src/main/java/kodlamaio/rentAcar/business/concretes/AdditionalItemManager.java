package kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.rentAcar.business.abstracts.AdditionalItemService;
import kodlamaio.rentAcar.business.request.additionalItems.CreateAdditionalItemRequest;
import kodlamaio.rentAcar.business.request.additionalItems.DeleteAdditionalItemRequest;
import kodlamaio.rentAcar.business.request.additionalItems.UpdateAdditionalItemRequest;
import kodlamaio.rentAcar.business.response.additionalItems.GetAdditionalItemResponse;
import kodlamaio.rentAcar.business.response.additionalItems.GetAllAdditionalItemsResponse;
import kodlamaio.rentAcar.core.utilities.exceptions.BusinessException;
import kodlamaio.rentAcar.core.utilities.mapping.ModelMapperService;
import kodlamaio.rentAcar.core.utilities.result.DataResult;
import kodlamaio.rentAcar.core.utilities.result.Result;
import kodlamaio.rentAcar.core.utilities.result.SuccessDataResult;
import kodlamaio.rentAcar.core.utilities.result.SuccessResult;
import kodlamaio.rentAcar.dataAccess.abstracts.AdditionalItemRepository;
import kodlamaio.rentAcar.entities.conretes.AdditionalItem;

@Service
public class AdditionalItemManager implements AdditionalItemService {
	@Autowired
	ModelMapperService modelMapperService;
	@Autowired
	private AdditionalItemRepository additionalItemRepository;

	@Override
	public Result add(CreateAdditionalItemRequest createAdditionalItemRequest) {
		checkIfAdditionalItemExistByName(createAdditionalItemRequest.getName());
		AdditionalItem additionalItem = this.modelMapperService.forRequest().map(createAdditionalItemRequest,
				AdditionalItem.class);
		this.additionalItemRepository.save(additionalItem);
		return new SuccessResult("ADDITIONAL.ITEM.ADDED");

	}

	@Override
	public Result update(UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		AdditionalItem additionalItemToUpdate = this.modelMapperService.forRequest().map(updateAdditionalItemRequest,
				AdditionalItem.class);
		this.additionalItemRepository.save(additionalItemToUpdate);
		return new SuccessResult("ADDITIONAL.ITEM.UPDATED");
	}

	@Override
	public Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
		this.additionalItemRepository.deleteById(deleteAdditionalItemRequest.getId());
		return new SuccessResult("ADDITIONAL.ITEM.DELETED");
	}

	@Override
	public DataResult<List<GetAllAdditionalItemsResponse>> getAll() {
		List<AdditionalItem> additionalItems = this.additionalItemRepository.findAll();
		List<GetAllAdditionalItemsResponse> response = additionalItems.stream()
				.map(additionalItem -> this.modelMapperService.forResponse().map(additionalItem,
						GetAllAdditionalItemsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAdditionalItemsResponse>>(response);
	}

	@Override
	public DataResult<GetAdditionalItemResponse> getById(int id) {
		AdditionalItem additionalItem = this.additionalItemRepository.getById(id);
		GetAdditionalItemResponse response = this.modelMapperService.forResponse().map(additionalItem,
				GetAdditionalItemResponse.class);
		return new SuccessDataResult<GetAdditionalItemResponse>(response);
	}

	private void checkIfAdditionalItemExistByName(String name) {
		AdditionalItem currentAdditionalItem = this.additionalItemRepository.findByName(name);
		if (currentAdditionalItem != null) {
			throw new BusinessException("ADDITIONAL.ITEM.EXIST");
		}
	}
}
