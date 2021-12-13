package br.com.samuel.productapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.samuel.productapi.dtos.supplier.SupplierRequest;
import br.com.samuel.productapi.dtos.supplier.SupplierResponse;
import br.com.samuel.productapi.services.Supplier.SupplierInterfaces;


@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

	
	@Autowired
	private SupplierInterfaces supplier;
	
	@PostMapping
	public SupplierResponse save(@RequestBody SupplierRequest request) {
		return supplier.save(request);
	}
}
