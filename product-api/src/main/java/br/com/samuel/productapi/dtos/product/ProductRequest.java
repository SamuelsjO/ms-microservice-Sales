package br.com.samuel.productapi.dtos.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductRequest {

	private String name;
	@JsonProperty("quantity_available")
	private Integer quantityAvailable;
	@JsonProperty("category")
	private Integer categoryId;
	@JsonProperty("supplier")
	private Integer supplierId;

}
