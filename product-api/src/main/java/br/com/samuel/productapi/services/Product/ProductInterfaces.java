package br.com.samuel.productapi.services.Product;

import java.util.Optional;

import br.com.samuel.productapi.dtos.product.ProductRequest;
import br.com.samuel.productapi.dtos.product.ProductResponse;
import br.com.samuel.productapi.models.Product;

public interface ProductInterfaces {

	Product findById(Integer id);

	ProductResponse save(ProductRequest request);
}
