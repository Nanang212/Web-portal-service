package com.example.demo.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Categories;
import com.example.demo.models.dto.request.CategoriesRequest;
import com.example.demo.repositories.CategoriesRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoriesService {
    private CategoriesRepository categoriesRepository;

    public List<Categories> getAll() {
        return categoriesRepository.findAll();
    }

    public Categories findById(Integer id) {
        return categoriesRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categories not found with id : " + id));
    }

    public Categories insertCategory(CategoriesRequest categoryRequest) {
        if (categoryRequest.getName() == null || categoryRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name must not be null or empty");
        }

        if (categoriesRepository.existsByName(categoryRequest.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category with the same name already exists");
        }
        Categories newCategory = new Categories();
        newCategory.setName(categoryRequest.getName());

        return categoriesRepository.save(newCategory);
    }

    public Categories updateCategory(Integer id, CategoriesRequest categoryRequest) {
        if (categoriesRepository.existsByNameAndIdNot(categoryRequest.getName(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category with the same name already exists");
        }

        Categories existingCategory = findById(id);

        existingCategory.setName(categoryRequest.getName());

        return categoriesRepository.save(existingCategory);
    }

    public void deleteCategory(Integer id) {
        Categories existingCategory = findById(id);
        categoriesRepository.delete(existingCategory);
    }

}
