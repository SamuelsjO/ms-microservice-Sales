package br.com.samuel.productapi.dtos.supplier;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import br.com.samuel.productapi.models.Supplier;

@Data
public class SupplierResponse {

	private Integer id;
	private String name;
	
	public static SupplierResponse of(Supplier supplier) {
		var response = new SupplierResponse();
		BeanUtils.copyProperties(supplier, response);
		return response;
	}
	
}
