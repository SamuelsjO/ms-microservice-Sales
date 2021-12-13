package br.com.samuel.productapi.services.impl.Product;

import java.util.Optional;
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
	public Product findByName(String name) {
		// TODO Auto-generated method stub
		return null;
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

}
