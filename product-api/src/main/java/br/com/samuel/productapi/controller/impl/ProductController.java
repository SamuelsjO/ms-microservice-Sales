package br.com.samuel.productapi.controller.impl;

import br.com.samuel.productapi.config.SuccessResponse;
import br.com.samuel.productapi.controller.ProductControllerInterface;
import br.com.samuel.productapi.dtos.product.ProductCheckStockRequest;
import br.com.samuel.productapi.dtos.product.ProductRequest;
import br.com.samuel.productapi.dtos.product.ProductResponse;
import br.com.samuel.productapi.dtos.product.ProductSalesResponse;
import br.com.samuel.productapi.services.Product.ProductInterfaces;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductControllerInterface {

    @Autowired
    private ProductInterfaces product;

    @Override
    public ProductResponse saveProduct(ProductRequest request) {
        return product.save(request);
    }

    @Override
    public ProductResponse findByIdProducts(Integer productId) {
        return product.findByIdResponse(productId);
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return product.findAll();
    }

    @Override
    public List<ProductResponse> findByNameProductIgnoreCaseContaining(String name) {
        return product.findByNameIgnoreCaseContaining(name);
    }

    @Override
    public List<ProductResponse> findByCategoryId(Integer categoryId) {

        return product.findByCategoryId(categoryId);
    }

    @Override
    public List<ProductResponse> findBySupplierId(Integer supplierId) {
        return product.findBySupplierId(supplierId);
    }

    @Override
    public ProductResponse updateProduct(ProductRequest request, Integer id) {
        return product.update(request, id);
    }

    @Override
    public SuccessResponse deleteProduct(Integer id) {
        return product.delete(id);
    }

    @Override
    public SuccessResponse checkProductStock(ProductCheckStockRequest request) {
        return product.checkProductsStock(request);
    }

    @Override
    public ProductSalesResponse findProductSales(Integer id) {
        return product.findProductSales(id);
    }
}
