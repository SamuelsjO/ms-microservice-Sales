package br.com.samuel.productapi.dtos.product;

import br.com.samuel.productapi.dtos.category.CategoryResponse;
import br.com.samuel.productapi.dtos.supplier.SupplierResponse;
import br.com.samuel.productapi.models.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ProductResponse {

	private Integer id;
	private String name;
	private Integer quantityAvailable;
	@JsonProperty("created_at")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime createdAt;
	private SupplierResponse supplier;
	private CategoryResponse category;

	public static ProductResponse of(Product product) {
		return ProductResponse
				.builder()
				.id(product.getId())
				.name(product.getName())
				.quantityAvailable(product.getQuantityAvailable())
				.createdAt(product.getCreatedAt())
				.supplier(SupplierResponse.of(product.getSupplier()))
				.category(CategoryResponse.of(product.getCategory()))
				.build();
	}

}
