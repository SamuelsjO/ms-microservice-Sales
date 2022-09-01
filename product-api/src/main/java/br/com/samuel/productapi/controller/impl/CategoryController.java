package br.com.samuel.productapi.controller.impl;

import br.com.samuel.productapi.config.SuccessResponse;
import br.com.samuel.productapi.controller.CategoryControllerInterface;
import br.com.samuel.productapi.dtos.category.CategoryRequest;
import br.com.samuel.productapi.dtos.category.CategoryResponse;
import br.com.samuel.productapi.services.category.CategoryInterfaces;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoryControllerInterface {

    @Autowired
    private CategoryInterfaces category;

    @Override
    public CategoryResponse saveCategory(CategoryRequest request) {
        return category.save(request);
    }

    @Override
    public CategoryResponse findByIdCategory(Integer id) {
        return category.findByIdResponse(id);
    }

    @Override
    public List<CategoryResponse> findByDescription(String description) {
        return category.findByDescriptionIgnoreCaseContaining(description);
    }

    @Override
    public List<CategoryResponse> findAllCategory() {
        return category.findAll();
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest request, Integer id) {
        return category.update(request, id);
    }

    @Override
    public SuccessResponse deleteCategory(Integer id) {
        return category.delete(id);
    }
}
