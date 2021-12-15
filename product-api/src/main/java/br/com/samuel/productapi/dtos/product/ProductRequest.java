package br.com.samuel.productapi.dtos.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductRequest {

	private String name;
	@JsonProperty("quantity_available")
	private Integer quantityAvailable;
	private Integer categoryId;
	private Integer supplierId;

}
