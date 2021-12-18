package br.com.samuel.productapi.services.impl.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.samuel.productapi.dtos.supplier.SupplierRequest;
import br.com.samuel.productapi.dtos.supplier.SupplierResponse;
import br.com.samuel.productapi.exception.ValidationException;
import br.com.samuel.productapi.models.Supplier;
import br.com.samuel.productapi.repository.SupplierRepository;
import br.com.samuel.productapi.services.Supplier.SupplierInterfaces;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class SupplierServicesImpl implements SupplierInterfaces {

	@Autowired
	private SupplierRepository repository;

	@Override
	public Supplier findById(Integer id) {
		validateInformedId(id);
		return repository.findById(id).orElseThrow(() -> new ValidationException("There's no supplier for the given ID"));
	}

	@Override
	public SupplierResponse save(SupplierRequest request) {
		validateSupplierNameInformed(request);
		var supplier = repository.save(Supplier.of(request));
		return SupplierResponse.of(supplier);
	}

	@Override
	public List<SupplierResponse> findByNameIgnoreCaseContaining(String name) {
		if(isEmpty(name)){
			throw new ValidationException("The supplier name must be informed");
		}
		return repository.findByNameIgnoreCaseContaining(name)
				.stream()
				.map(SupplierResponse::of)
				.collect(Collectors.toList());
	}

	@Override
	public SupplierResponse findByIdResponse(Integer id) {
		if(isEmpty(id)){
			throw new ValidationException("The supplier ID must be informed");
		}
		return SupplierResponse.of(findById(id));
	}

	@Override
	public List<SupplierResponse> findAll() {
		return repository
				.findAll()
				.stream()
				.map(SupplierResponse::of)
				.collect(Collectors.toList());
	}

	private void validateSupplierNameInformed(SupplierRequest request) {
		if (isEmpty(request.getName())) {
			throw new ValidationException("Supplier not informed");
		}
	}

	private void validateInformedId(Integer id) {
		if (isEmpty(id)) {
			throw new ValidationException("The supplier ID must be informed.");
		}
	}
}
