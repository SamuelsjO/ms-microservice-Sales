package br.com.samuel.productapi.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "FK_SUPPLIER", nullable = false)
	private Supplier supplier;
	
	@ManyToOne
	@JoinColumn(name = "FK_CATEGORY", nullable = false)
	private Category category;

	@Column(name = "QUANTITY_AVAILABLE", nullable = false)
	private Integer quantitiyAvailable;

	public Product() {
		super();
	}

	public Product(Integer id, String name, Supplier supplier, Category category, Integer quantitiyAvailable) {
		super();
		this.id = id;
		this.name = name;
		this.supplier = supplier;
		this.category = category;
		this.quantitiyAvailable = quantitiyAvailable;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getQuantitiyAvailable() {
		return quantitiyAvailable;
	}

	public void setQuantitiyAvailable(Integer quantitiyAvailable) {
		this.quantitiyAvailable = quantitiyAvailable;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, id, name, quantitiyAvailable, supplier);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(category, other.category) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(quantitiyAvailable, other.quantitiyAvailable)
				&& Objects.equals(supplier, other.supplier);
	}
	
	
}
