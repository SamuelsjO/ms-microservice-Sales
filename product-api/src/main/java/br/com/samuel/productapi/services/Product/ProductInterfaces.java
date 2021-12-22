package br.com.samuel.productapi.services.Product;

import java.util.List;

import br.com.samuel.productapi.config.SuccessResponse;
import br.com.samuel.productapi.dtos.product.ProductRequest;
import br.com.samuel.productapi.dtos.product.ProductResponse;
import br.com.samuel.productapi.dtos.product.ProductStockDTO;
import br.com.samuel.productapi.models.Product;

public interface ProductInterfaces {

	Product findById(Integer id);

	ProductResponse save(ProductRequest request);
	ProductResponse update(ProductRequest request, Integer id);
	List<ProductResponse> findByNameIgnoreCaseContaining(String name);
	List<ProductResponse> findByCategoryId(Integer categoryId);
	List<ProductResponse> findBySupplierId(Integer supplierId);
    ProductResponse findByIdResponse(Integer id);
	List<ProductResponse> findAll();
	Boolean existCategoryId(Integer id);
	Boolean existSupplierId(Integer id);
	SuccessResponse delete(Integer id);
	public void updateProductStock(ProductStockDTO productStockDTO);
}
