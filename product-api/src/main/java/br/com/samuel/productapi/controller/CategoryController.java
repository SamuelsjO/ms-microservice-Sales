package br.com.samuel.productapi.controller;

import br.com.samuel.productapi.config.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.samuel.productapi.dtos.category.CategoryRequest;
import br.com.samuel.productapi.dtos.category.CategoryResponse;
import br.com.samuel.productapi.services.category.CategoryInterfaces;

import java.util.List;

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
	public CategoryResponse findById(@PathVariable Integer id){
		return category.findByIdResponse(id);
	}

	@GetMapping("description/{description}")
	public List<CategoryResponse> findByDescription(@PathVariable String description){
		return category.findByDescriptionIgnoreCaseContaining(description);
	}

	@GetMapping()
	public List<CategoryResponse> findAll(){
		return category.findAll();
	}


	@PutMapping("{id}")
	public CategoryResponse update(@RequestBody CategoryRequest request, @PathVariable Integer id){
		return category.update(request,id);
	}

	@DeleteMapping("{id}")
	public SuccessResponse delete(@PathVariable Integer id){
		return category.delete(id);
	}
}
