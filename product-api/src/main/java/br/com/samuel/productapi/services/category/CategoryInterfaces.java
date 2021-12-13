package br.com.samuel.productapi.services.category;

import java.util.Optional;

import br.com.samuel.productapi.dtos.category.CategoryRequest;
import br.com.samuel.productapi.dtos.category.CategoryResponse;
import br.com.samuel.productapi.models.Category;

public interface CategoryInterfaces {
	
	Optional<Category> findById(Integer id);
	
	CategoryResponse save(CategoryRequest request);

}
