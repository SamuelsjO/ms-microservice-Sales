package br.com.samuel.productapi.services.impl.Supplier;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.samuel.productapi.dtos.supplier.SupplierRequest;
import br.com.samuel.productapi.dtos.supplier.SupplierResponse;
import br.com.samuel.productapi.exception.ValidationException;
import br.com.samuel.productapi.models.Supplier;
import br.com.samuel.productapi.repository.SupplierRepository;
import br.com.samuel.productapi.services.Supplier.SupplierInterfaces;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class SupplierServicesImpl implements SupplierInterfaces {

	@Autowired
	private SupplierRepository repository;

	@Override
	public Supplier findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ValidationException("There's no supplier for the given ID"));
	}

	@Override
	public SupplierResponse save(SupplierRequest request) {
		validateSupplierNameInformed(request);
		var supplier = repository.save(Supplier.of(request));

		return SupplierResponse.of(supplier);
	}

	private void validateSupplierNameInformed(SupplierRequest request) {
		if (isEmpty(request.getName())) {
			throw new ValidationException("Supplier not informed");
		}
	}
}
