package br.com.samuel.productapi.dtos.supplier;

import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.com.samuel.productapi.models.Supplier;


public class SupplierResponse {

	private Integer id;
	private String name;
	
	public static SupplierResponse of(Supplier supplier) {
		var response = new SupplierResponse();
		BeanUtils.copyProperties(supplier, response);
		return response;
		
	}

	public String getDescription() {
		return name;
	}

	public void setDescription(String description) {
		this.name = description;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SupplierResponse other = (SupplierResponse) obj;
		return Objects.equals(name, other.name) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "SupplierResponse [id=" + id + ", description=" + name + "]";
	}
	
	
	
}
