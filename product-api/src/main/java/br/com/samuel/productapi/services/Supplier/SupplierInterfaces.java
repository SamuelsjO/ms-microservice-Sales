package br.com.samuel.productapi.services.Supplier;

import java.util.List;
import java.util.Optional;

import br.com.samuel.productapi.dtos.category.CategoryResponse;
import br.com.samuel.productapi.dtos.supplier.SupplierRequest;
import br.com.samuel.productapi.dtos.supplier.SupplierResponse;
import br.com.samuel.productapi.models.Supplier;

public interface SupplierInterfaces {

	Supplier findById(Integer id);

	SupplierResponse save(SupplierRequest request);

	List<SupplierResponse> findByNameIgnoreCaseContaining(String name);

	SupplierResponse findByIdResponse(Integer id);

	List<SupplierResponse> findAll();
}
