package br.com.samuel.productapi.models;

import br.com.samuel.productapi.dtos.product.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "FK_SUPPLIER",nullable = false)
	private Supplier supplier;

	@ManyToOne
	@JoinColumn(name = "FK_CATEGORY", nullable = false)
	private Category category;

	@Column(name = "QUANTITY_AVAILABLE", nullable = false)
	private Integer quantityAvailable;

	@Column(name = "CREATE_AT", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@PrePersist
	public void prePersist(){
		createdAt = LocalDateTime.now();
	}

	public static Product of(ProductRequest request, Supplier supplier, Category category){
		return Product
				.builder()
				.name(request.getName())
				.quantityAvailable(request.getQuantityAvailable())
				.supplier(supplier)
				.category(category)
				.build();
	}
}
