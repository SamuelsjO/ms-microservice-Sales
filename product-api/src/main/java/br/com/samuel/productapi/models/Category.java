package br.com.samuel.productapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import org.springframework.beans.BeanUtils;
import br.com.samuel.productapi.dtos.category.CategoryRequest;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CATEGORY")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "DESCRIPTION")
	private String description;

	public static Category of(CategoryRequest request) {
		var category = new Category();
		BeanUtils.copyProperties(request, category);
		return category;
		
	}
}
