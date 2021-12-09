package br.com.samuel.productapi.services.impl.Product;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.samuel.productapi.models.Product;
import br.com.samuel.productapi.services.Product.ProductInterfaces;

@Service
public class ProductServicesImpl implements ProductInterfaces {

	@Override
	public Optional<Product> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
