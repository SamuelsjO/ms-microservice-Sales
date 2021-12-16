package br.com.samuel.productapi.services.category;

import br.com.samuel.productapi.dtos.category.CategoryRequest;
import br.com.samuel.productapi.dtos.category.CategoryResponse;
import br.com.samuel.productapi.models.Category;

import java.util.List;

public interface CategoryInterfaces {

	CategoryResponse save(CategoryRequest request);
	Category findById(Integer id);
	CategoryResponse findByIdResponse(Integer id);
	List<CategoryResponse> findAll();
	List<CategoryResponse> findByDescriptionIgnoreCaseContaining(String description);
}
