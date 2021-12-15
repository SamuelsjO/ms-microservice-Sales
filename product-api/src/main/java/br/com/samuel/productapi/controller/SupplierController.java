package br.com.samuel.productapi.controller;

import br.com.samuel.productapi.models.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("{id}")
	public Supplier findById(@PathVariable Integer id){
		return supplier.findById(id);
	}
}
