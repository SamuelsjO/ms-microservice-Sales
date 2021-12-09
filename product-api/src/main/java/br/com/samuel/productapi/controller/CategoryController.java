package br.com.samuel.productapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.samuel.productapi.dtos.CategoryRequest;
import br.com.samuel.productapi.dtos.CategoryResponse;
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
}
