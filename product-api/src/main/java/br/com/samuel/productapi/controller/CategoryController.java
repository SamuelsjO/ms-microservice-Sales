package br.com.samuel.productapi.controller;

import br.com.samuel.productapi.config.SuccessResponse;
import br.com.samuel.productapi.dtos.category.CategoryRequest;
import br.com.samuel.productapi.dtos.category.CategoryResponse;
import br.com.samuel.productapi.services.category.CategoryInterfaces;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(tags = "Endpoint category product", authorizations = @Authorization("Authorization"))
@RequestMapping(value = "/v1/api/category",
        consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
        produces = APPLICATION_JSON_VALUE)
public class CategoryController {

    @Autowired
    private CategoryInterfaces category;

    @PostMapping(
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public CategoryResponse save(@RequestBody CategoryRequest request) {
        return category.save(request);
    }

    @GetMapping(value = "{id}",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public CategoryResponse findById(@PathVariable Integer id) {
        return category.findByIdResponse(id);
    }

    @GetMapping(value = "description/{description}",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public List<CategoryResponse> findByDescription(@PathVariable String description) {
        return category.findByDescriptionIgnoreCaseContaining(description);
    }

    @ApiOperation(value = "Busca todas as catogias de produtos")
    @GetMapping(
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public List<CategoryResponse> findAll() {
        return category.findAll();
    }


    @PutMapping(value = "{id}",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public CategoryResponse update(@RequestBody CategoryRequest request, @PathVariable Integer id) {
        return category.update(request, id);
    }

    @DeleteMapping(value = "{id}",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public SuccessResponse delete(@PathVariable Integer id) {
        return category.delete(id);
    }
}
