package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Article;
import com.example.demo.models.Categories;
import com.example.demo.models.dto.request.ArticleRequest;
import com.example.demo.models.dto.request.CategoriesRequest;
import com.example.demo.services.ArticlesService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/articles")
public class ArticlesController {
    private ArticlesService articlesService;

    @GetMapping
    public List<Article> getAll() {
        return articlesService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Integer id) {
        Article article = articlesService.findById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PostMapping
    public Article insertCountryDto(@RequestBody ArticleRequest articleRequest) {
        return articlesService.insertArticle(articleRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updatedArticle(@PathVariable Integer id,
            @RequestBody ArticleRequest articleRequest) {
        Article updatedArticle = articlesService.updateArticle(id, articleRequest);
        return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Integer id) {
        articlesService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{articleId}/categories/{categoryId}")
    public ResponseEntity<Void> connectArticleWithCategory(@PathVariable Integer articleId,
                                                           @PathVariable Integer categoryId) {
        articlesService.connectArticleWithCategories(articleId, categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
