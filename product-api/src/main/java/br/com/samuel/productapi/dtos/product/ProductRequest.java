package br.com.samuel.productapi.dtos.product;


import java.util.Objects;

import lombok.Getter;

@Getter
public class ProductRequest {

    private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductRequest other = (ProductRequest) obj;
		return Objects.equals(description, other.description);
	}

	@Override
	public String toString() {
		return "CategoryRequest [description=" + description + "]";
	}
    
    

}
