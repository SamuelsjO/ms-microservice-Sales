package br.com.samuel.productapi.services.category;

import br.com.samuel.productapi.dtos.category.CategoryRequest;
import br.com.samuel.productapi.dtos.category.CategoryResponse;
import br.com.samuel.productapi.models.Category;

public interface CategoryInterfaces {

	Category findById(Integer id);
	
	CategoryResponse save(CategoryRequest request);

}
