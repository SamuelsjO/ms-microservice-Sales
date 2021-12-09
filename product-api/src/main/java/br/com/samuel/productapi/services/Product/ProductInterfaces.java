package br.com.samuel.productapi.services.Product;

import java.util.Optional;

import br.com.samuel.productapi.models.Product;

public interface ProductInterfaces {

	Optional<Product> findByName(String name);
}
