package br.com.samuel.productapi.controller;

import br.com.samuel.productapi.config.SuccessResponse;
import br.com.samuel.productapi.dtos.product.ProductCheckStockRequest;
import br.com.samuel.productapi.dtos.product.ProductRequest;
import br.com.samuel.productapi.dtos.product.ProductResponse;
import br.com.samuel.productapi.dtos.product.ProductSalesResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = "Endpoint product", authorizations = @Authorization("Authorization"))
@RequestMapping(value = "/v1/api/product",
        consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
        produces = APPLICATION_JSON_VALUE)
public interface ProductControllerInterface {

    @PostMapping(
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    ProductResponse saveProduct(@RequestBody ProductRequest request);

    @GetMapping(
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    ProductResponse findByIdProducts(@RequestParam Integer id);

    @GetMapping( value = "allProducts",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public List<ProductResponse> findAllProducts();

    @GetMapping(value = "name",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    List<ProductResponse> findByNameProductIgnoreCaseContaining(@RequestParam String name);

    @GetMapping(value = "category",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    List<ProductResponse> findByCategoryId(@RequestParam Integer categoryId);

    @GetMapping(value = "supplier",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    List<ProductResponse> findBySupplierId(@RequestParam Integer supplierId);

    @PutMapping(
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    ProductResponse updateProduct(@RequestBody ProductRequest request, @RequestParam Integer id);

    @DeleteMapping(
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    SuccessResponse deleteProduct(@RequestParam Integer id);

    @PostMapping(value = "check-stock",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    SuccessResponse checkProductStock(@RequestBody ProductCheckStockRequest request);

    @GetMapping(value = "{id}/sales",
            consumes = {ALL_VALUE, APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    ProductSalesResponse findProductSales(@PathVariable Integer id);
}
