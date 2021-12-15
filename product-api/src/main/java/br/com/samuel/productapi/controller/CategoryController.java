package br.com.samuel.productapi.controller;

import br.com.samuel.productapi.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.samuel.productapi.dtos.category.CategoryRequest;
import br.com.samuel.productapi.dtos.category.CategoryResponse;
import br.com.samuel.productapi.services.category.CategoryInterfaces;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryInterfaces category;
	
	@PostMapping
	public CategoryResponse save(@RequestBody CategoryRequest request) {
		return category.save(request);
	}

	@GetMapping("{id}")
	public Category findById(@PathVariable Integer id){
		return category.findById(id);
	}
}
