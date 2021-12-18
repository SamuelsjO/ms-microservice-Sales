package br.com.samuel.productapi.controller;

import br.com.samuel.productapi.models.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.samuel.productapi.dtos.supplier.SupplierRequest;
import br.com.samuel.productapi.dtos.supplier.SupplierResponse;
import br.com.samuel.productapi.services.Supplier.SupplierInterfaces;

import java.util.List;


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
	public SupplierResponse findById(@PathVariable Integer id) {
		return supplier.findByIdResponse(id);
	}

	@GetMapping()
	public List<SupplierResponse> findAll(){
		return supplier.findAll();
	}

	@GetMapping("name/{name}")
	public List<SupplierResponse> findByNameIgnoreCaseContaining(@PathVariable String name){
		return supplier.findByNameIgnoreCaseContaining(name);
	}
}
