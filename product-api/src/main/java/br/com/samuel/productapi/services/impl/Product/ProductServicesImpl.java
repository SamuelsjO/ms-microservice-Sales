package br.com.samuel.productapi.services.impl.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.samuel.productapi.config.SuccessResponse;
import br.com.samuel.productapi.dtos.product.ProductRequest;
import br.com.samuel.productapi.dtos.product.ProductResponse;
import br.com.samuel.productapi.exception.ValidationException;
import br.com.samuel.productapi.repository.ProductRepository;
import br.com.samuel.productapi.services.Supplier.SupplierInterfaces;
import br.com.samuel.productapi.services.category.CategoryInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.samuel.productapi.models.Product;
import br.com.samuel.productapi.services.Product.ProductInterfaces;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class ProductServicesImpl implements ProductInterfaces {

	private static final Integer ZERO = 0;

	@Autowired
	private ProductRepository repository;
	@Autowired
	private SupplierInterfaces supplierInterfaces;
	@Autowired
	private CategoryInterfaces categoryInterfaces;

	@Override
	public Product findById(Integer id) {
		validateInformedId(id);
		return repository.findById(id).orElseThrow(() -> new ValidationException("There's no product for the given ID"));
	}

	@Override
	public ProductResponse save(ProductRequest request) {
		validateProductDataInformed(request);
		validateCategoryAndSupplierInformed(request);

		var category = categoryInterfaces.findById(request.getCategoryId());
		var supplier = supplierInterfaces.findById(request.getSupplierId());

		var product = repository.save(Product.of(request, supplier, category));
		return ProductResponse.of(product);
	}

	@Override
	public ProductResponse update(ProductRequest request, Integer id) {
		validateProductDataInformed(request);
		validateCategoryAndSupplierInformed(request);
		validateInformedId(id);

		var category = categoryInterfaces.findById(request.getCategoryId());
		var supplier = supplierInterfaces.findById(request.getSupplierId());

		var product = Product.of(request, supplier, category);
		product.setId(id);
		repository.save(product);

		return ProductResponse.of(product);
	}

	@Override
	public List<ProductResponse> findByNameIgnoreCaseContaining(String name) {
		if(isEmpty(name)){
			throw new ValidationException("The supplier name must be informed");
		}
		return repository.findByNameIgnoreCaseContaining(name)
				.stream()
				.map(ProductResponse::of)
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductResponse> findByCategoryId(Integer categoryId) {
		if(isEmpty(categoryId)){
			throw new ValidationException("The product category ID must be informed");
		}
		return repository
				.findByCategoryId(categoryId)
				.stream()
				.map(ProductResponse::of)
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductResponse> findBySupplierId(Integer supplierId) {
		if(isEmpty(supplierId)){
			throw new ValidationException("The product supplier ID must be informed");
		}
		return repository
				.findBySupplierId(supplierId)
				.stream()
				.map(ProductResponse::of)
				.collect(Collectors.toList());
	}

	@Override
	public ProductResponse findByIdResponse(Integer id) {
		validateInformedId(id);
		return ProductResponse.of(findById(id));
	}

	@Override
	public List<ProductResponse> findAll() {
		return repository.findAll()
				.stream()
				.map(ProductResponse::of)
				.collect(Collectors.toList());
	}

	@Override
	public Boolean existCategoryId(Integer id) {
		return repository.existsByCategoryId(id);
	}

	@Override
	public Boolean existSupplierId(Integer id) {
		return repository.existsBySupplierId(id);
	}

	@Override
	public SuccessResponse delete(Integer id) {
		validateInformedId(id);
		repository.deleteById(id);
		return SuccessResponse.create("The product wa delete");
	}

	private void validateProductDataInformed(ProductRequest request) {
		if(isEmpty(request.getName())){
			throw new ValidationException("The product description was not informed. ");
		}

		if(isEmpty(request.getQuantityAvailable())){
			throw new ValidationException("The product quantityAvailable was not informed. ");
		}

		if(isEmpty(request.getQuantityAvailable() <= ZERO)){
			throw new ValidationException("The quantity should not be less or equal a zero. ");
		}
	}

	private void validateCategoryAndSupplierInformed(ProductRequest request) {
		if(isEmpty(request.getSupplierId())){
			throw new ValidationException("The product supplier ID was not informed. ");
		}

		if(isEmpty(request.getCategoryId())){
			throw new ValidationException("The product category ID was not informed. ");
		}
	}

	private void validateInformedId(Integer id) {
		if (isEmpty(id)) {
			throw new ValidationException("The product ID must be informed.");
		}
	}

}
