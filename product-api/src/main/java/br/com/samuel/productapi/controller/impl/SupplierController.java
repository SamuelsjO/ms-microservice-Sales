package br.com.samuel.productapi.controller.impl;

import br.com.samuel.productapi.config.SuccessResponse;
import br.com.samuel.productapi.controller.SupplierControllerInterface;
import br.com.samuel.productapi.dtos.supplier.SupplierRequest;
import br.com.samuel.productapi.dtos.supplier.SupplierResponse;
import br.com.samuel.productapi.services.Supplier.SupplierInterfaces;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class SupplierController implements SupplierControllerInterface {

	@Autowired
	private SupplierInterfaces supplier;

	@Override
	public SupplierResponse saveSupplier(SupplierRequest request) {
		return supplier.save(request);
	}

	@Override
	public SupplierResponse findByIdSupplier(Integer id) {
		return supplier.findByIdResponse(id);
	}

	@Override
	public List<SupplierResponse> findaAllSupplier() {
		return supplier.findAll();
	}

	@Override
	public List<SupplierResponse> findByNameIgnoreCaseContaining(String name) {
		return supplier.findByNameIgnoreCaseContaining(name);
	}

	@Override
	public SupplierResponse updateSupplier(SupplierRequest request, Integer id) {
		return supplier.update(request, id);
	}

	@Override
	public SuccessResponse deleteSupplier(Integer id) {
		return supplier.delete(id);
	}

}