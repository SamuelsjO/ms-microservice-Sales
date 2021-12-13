package br.com.samuel.productapi.dtos.supplier;


import java.util.Objects;

import lombok.Getter;

@Getter
public class SupplierRequest {

    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SupplierRequest other = (SupplierRequest) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "SupplierRequest [name=" + name + "]";
	}


}
