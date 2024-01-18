package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer > {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
