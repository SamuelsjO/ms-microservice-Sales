package br.com.samuel.productapi.dtos.category;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import br.com.samuel.productapi.models.Category;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

	private Integer id;
	private String description;
	
	public static CategoryResponse of(Category category) {
		var response = new CategoryResponse();
		BeanUtils.copyProperties(category, response);
		return response;
		
	}
}
