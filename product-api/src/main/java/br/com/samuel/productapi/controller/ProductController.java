package br.com.samuel.productapi.controller;


import br.com.samuel.productapi.dtos.product.ProductRequest;
import br.com.samuel.productapi.dtos.product.ProductResponse;
import br.com.samuel.productapi.models.Product;
import br.com.samuel.productapi.services.Product.ProductInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Product findById(@PathVariable Integer id){
        return product.findById(id);
    }

}
