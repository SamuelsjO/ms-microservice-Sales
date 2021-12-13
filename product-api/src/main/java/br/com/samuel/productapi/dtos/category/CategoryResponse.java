package br.com.samuel.productapi.dtos.category;

import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.samuel.productapi.models.Category;
import lombok.Data;


public class CategoryResponse {

	private Integer id;
	private String description;
	
	public static CategoryResponse of(Category category) {
		var response = new CategoryResponse();
		BeanUtils.copyProperties(category, response);
		return response;
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryResponse other = (CategoryResponse) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "CategoryResponse [id=" + id + ", description=" + description + "]";
	}
	
	
	
}
