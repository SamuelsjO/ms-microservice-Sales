package br.com.samuel.productapi.controller;

import br.com.samuel.productapi.dtos.category.CategoryResponse;
import br.com.samuel.productapi.dtos.product.ProductRequest;
import br.com.samuel.productapi.dtos.product.ProductResponse;
import br.com.samuel.productapi.models.Product;
import br.com.samuel.productapi.services.Product.ProductInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductInterfaces product;

    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest request) {
        return product.save(request);
    }

    @GetMapping("{id}")
    public ProductResponse findById(@PathVariable Integer id){
        return product.findByIdResponse(id);
    }

    @GetMapping()
    public List<ProductResponse> findAll(){
        return product.findAll();
    }

    @GetMapping("name/{name}")
    public List<ProductResponse> findByNameIgnoreCaseContaining(@PathVariable String name){
        return product.findByNameIgnoreCaseContaining(name);
    }

    @GetMapping("category/{categoryId}")
    public List<ProductResponse> findByCategoryId(@PathVariable Integer categoryId){
        return product.findByCategoryId(categoryId);
    }

    @GetMapping("supplier/{supplierId}")
    public List<ProductResponse> findBySupplierId(@PathVariable Integer supplierId){
        return product.findBySupplierId(supplierId);
    }
}
