package br.com.samuel.productapi.services.Supplier;

import java.util.Optional;

import br.com.samuel.productapi.models.Supplier;

public interface SupplierInterfaces {

	Optional<Supplier> findById(Integer id);
}
