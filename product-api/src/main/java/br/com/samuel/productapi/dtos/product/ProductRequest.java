package br.com.samuel.productapi.dtos.product;

import lombok.Data;

@Data
public class ProductRequest {
	private Integer id;
	private String name;
	private Integer supplierId;
	private Integer quantityAvailable;
	private Integer categoryId;

}
