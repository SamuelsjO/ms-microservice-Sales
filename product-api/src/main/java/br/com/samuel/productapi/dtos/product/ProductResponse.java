package br.com.samuel.productapi.dtos.product;

import java.util.Objects;

import br.com.samuel.productapi.dtos.category.CategoryResponse;
import br.com.samuel.productapi.dtos.supplier.SupplierResponse;
import br.com.samuel.productapi.models.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

	private Integer id;
	private String name;
	private SupplierResponse supplier;
	private CategoryResponse category;

	public ProductResponse() {
	}

//	public static ProductResponse of(Product product) {
//		return ProductResponse
//		
//
//	}

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

	public SupplierResponse getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierResponse supplier) {
		this.supplier = supplier;
	}

	public CategoryResponse getCategory() {
		return category;
	}

	public void setCategory(CategoryResponse category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, id, name, supplier);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductResponse other = (ProductResponse) obj;
		return Objects.equals(category, other.category) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(supplier, other.supplier);
	}

	@Override
	public String toString() {
		return "ProductResponse [id=" + id + ", name=" + name + ", supplier=" + supplier + ", category=" + category
				+ "]";
	}

}
