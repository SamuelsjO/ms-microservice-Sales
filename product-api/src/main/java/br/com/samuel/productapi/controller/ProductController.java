package br.com.samuel.productapi.controller;

import br.com.samuel.productapi.config.SuccessResponse;
import br.com.samuel.productapi.dtos.product.ProductCheckStockRequest;
import br.com.samuel.productapi.dtos.product.ProductRequest;
import br.com.samuel.productapi.dtos.product.ProductResponse;
import br.com.samuel.productapi.dtos.product.ProductSalesResponse;
import br.com.samuel.productapi.services.Product.ProductInterfaces;
import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(tags = "Endpoint category product", authorizations = @Authorization("Authorization"))
@RequestMapping(value = "/v1/api/product",
        consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
        produces = APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductInterfaces product;

    @PostMapping(
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public ProductResponse save(@RequestBody ProductRequest request) {
        return product.save(request);
    }

    @GetMapping(value = "{id}",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public ProductResponse findById(@PathVariable Integer id) {
        return product.findByIdResponse(id);
    }

    @GetMapping(
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public List<ProductResponse> findAll() {
        return product.findAll();
    }

    @GetMapping(value = "name/{name}",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public List<ProductResponse> findByNameIgnoreCaseContaining(@PathVariable String name) {
        return product.findByNameIgnoreCaseContaining(name);
    }

    @GetMapping(value = "category/{categoryId}",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public List<ProductResponse> findByCategoryId(@PathVariable Integer categoryId) {
        return product.findByCategoryId(categoryId);
    }

    @GetMapping(value = "supplier/{supplierId}",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public List<ProductResponse> findBySupplierId(@PathVariable Integer supplierId) {
        return product.findBySupplierId(supplierId);
    }

    @PutMapping(value = "{id}",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public ProductResponse update(@RequestBody ProductRequest request, @PathVariable Integer id) {
        return product.update(request, id);
    }

    @DeleteMapping(value = "{id}",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public SuccessResponse delete(@PathVariable Integer id) {
        return product.delete(id);
    }

    @PostMapping(value = "check-stock",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public SuccessResponse checkProductStock(@RequestBody ProductCheckStockRequest request) {
        return product.checkProductsStock(request);
    }

    @GetMapping(value = "{id}/sales",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public ProductSalesResponse findProductSales(@PathVariable Integer id) {
        return product.findProductSales(id);
    }
}
