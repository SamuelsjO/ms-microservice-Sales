package br.com.samuel.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.samuel.productapi.models.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{


    List<Category> findByDescriptionIgnoreCaseContaining(String description);
}
