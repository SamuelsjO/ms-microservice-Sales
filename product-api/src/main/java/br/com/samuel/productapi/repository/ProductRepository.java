package br.com.samuel.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.samuel.productapi.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
