package br.com.samuel.productapi.services.impl.category;

import br.com.samuel.productapi.config.SuccessResponse;
import br.com.samuel.productapi.dtos.supplier.SupplierRequest;
import br.com.samuel.productapi.dtos.supplier.SupplierResponse;
import br.com.samuel.productapi.services.Product.ProductInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.samuel.productapi.dtos.category.CategoryRequest;
import br.com.samuel.productapi.dtos.category.CategoryResponse;
import br.com.samuel.productapi.exception.ValidationException;
import br.com.samuel.productapi.models.Category;
import br.com.samuel.productapi.repository.CategoryRepository;
import br.com.samuel.productapi.services.category.CategoryInterfaces;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class CategoryServicesImpl implements CategoryInterfaces {
	
	@Autowired
	private CategoryRepository repository;

	@Autowired
	private ProductInterfaces product;

	@Override
	public Category findById(Integer id) {
	validateInformedId(id);
       return repository
			   .findById(id).
			   orElseThrow(() ->
					   new ValidationException("There's no category for the given ID"));

	}

	@Override
	public CategoryResponse findByIdResponse(Integer id) {
		return CategoryResponse.of(findById(id));
	}

	@Override
	public List<CategoryResponse> findAll() {
		return repository.findAll().stream().map(category -> CategoryResponse.of(category)).collect(Collectors.toList());
	}

	@Override
	public List<CategoryResponse> findByDescriptionIgnoreCaseContaining(String description) {
		if(isEmpty(description)){
			throw new ValidationException("The category description must be informed");
		}
		return repository.findByDescriptionIgnoreCaseContaining(description).stream().map(category -> CategoryResponse.of(category)).collect(Collectors.toList());
	}

	@Override
	public SuccessResponse delete(Integer id) {
		validateInformedId(id);
		if(product.existCategoryId(id)){
			throw new ValidationException("You cannot delete this category because it's already defined by a product");

		}
		repository.deleteById(id);
		return SuccessResponse.create("The category was delete");
	}


	@Override
	public CategoryResponse save(CategoryRequest request) {
		validateCategoryNameInformed(request);
		var category = repository.save(Category.of(request));
		return CategoryResponse.of(category);
	}

	@Override
	public CategoryResponse update(CategoryRequest request, Integer id) {
		validateCategoryNameInformed(request);
		validateInformedId(id);
		var category = Category.of(request);
		category.setId(id);
		repository.save(category);
		return CategoryResponse.of(category);
	}


	private void validateCategoryNameInformed(CategoryRequest request) {
		if(isEmpty(request.getDescription())){
			throw new ValidationException("The category description was not informed. ");
		}
	}

	private void validateInformedId(Integer id) {
		if (isEmpty(id)) {
			throw new ValidationException("The category ID must be informed.");
		}
	}
}
