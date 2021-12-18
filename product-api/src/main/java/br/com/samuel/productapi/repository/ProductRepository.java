package br.com.samuel.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.samuel.productapi.models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameIgnoreCaseContaining(String name);
    List<Product> findByCategoryId(Integer id);
    List<Product> findBySupplierId(Integer id);
    Boolean existsByCategoryId(Integer id);

    Boolean existsBySupplierId(Integer id);

}
