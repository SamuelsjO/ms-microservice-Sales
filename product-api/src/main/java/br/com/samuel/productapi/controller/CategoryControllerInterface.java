package br.com.samuel.productapi.controller;

import br.com.samuel.productapi.config.SuccessResponse;
import br.com.samuel.productapi.dtos.category.CategoryRequest;
import br.com.samuel.productapi.dtos.category.CategoryResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = "Endpoint category", authorizations = @Authorization("Authorization"))
@RequestMapping(value = "/v1/api/category",
        consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
        produces = APPLICATION_JSON_VALUE)
public interface CategoryControllerInterface {

    @PostMapping(
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    CategoryResponse saveCategory(@RequestBody CategoryRequest request);


    @GetMapping(
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    CategoryResponse findByIdCategory(@RequestParam Integer id);


    @GetMapping(value = "/description",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    List<CategoryResponse> findByDescription(@RequestParam String description);

    @GetMapping( value = "/all",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    List<CategoryResponse> findAllCategory();


    @PutMapping(
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    CategoryResponse updateCategory(@RequestBody CategoryRequest request, @RequestParam Integer id);


    @DeleteMapping(
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    SuccessResponse deleteCategory(@RequestParam Integer id);
}
