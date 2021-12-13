package br.com.samuel.productapi.services.Supplier;

import java.util.Optional;

import br.com.samuel.productapi.dtos.supplier.SupplierRequest;
import br.com.samuel.productapi.dtos.supplier.SupplierResponse;
import br.com.samuel.productapi.models.Supplier;

public interface SupplierInterfaces {

	Supplier findById(Integer id);

	SupplierResponse save(SupplierRequest request);
}
