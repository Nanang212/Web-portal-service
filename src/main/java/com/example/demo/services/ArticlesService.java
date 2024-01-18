package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Article;
import com.example.demo.models.Categories;
import com.example.demo.models.dto.request.ArticleRequest;
import com.example.demo.repositories.ArticlesRepository;
import com.example.demo.repositories.CategoriesRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticlesService {
    private ArticlesRepository articlesRepository;
    private CategoriesRepository categoriesRepository;

    public List<Article> getAll() {
        return articlesRepository.findAll();
    }

    public Article findById(Integer id) {
        return articlesRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categories not found with id : " + id));
    }

    public Article insertArticle(ArticleRequest articleRequest) {
        Article newArticle = new Article();
        newArticle.setTitle(articleRequest.getTitle());
        newArticle.setSlug(articleRequest.getSlug());
        newArticle.setBody(articleRequest.getBody());
        newArticle.setBanner(articleRequest.getBanner());
        newArticle.setImage(articleRequest.getImage());
        newArticle.setCounter(articleRequest.getCounter());
        newArticle.setType(articleRequest.getType());
        newArticle.setStatus(articleRequest.getStatus());
        newArticle.setIsSlideShow(articleRequest.getIsSlideShow());
        newArticle.setCreatedAt(LocalDateTime.now());
        newArticle.setUpdatedAt(LocalDateTime.now());

        return articlesRepository.save(newArticle);
    }

    public Article updateArticle(Integer id, ArticleRequest articleRequest) {
        Article existingArticle = findById(id);

        existingArticle.setTitle(articleRequest.getTitle());
        existingArticle.setSlug(articleRequest.getSlug());
        existingArticle.setBody(articleRequest.getBody());
        existingArticle.setBanner(articleRequest.getBanner());
        existingArticle.setImage(articleRequest.getImage());
        existingArticle.setCounter(articleRequest.getCounter());
        existingArticle.setType(articleRequest.getType());
        existingArticle.setStatus(articleRequest.getStatus());
        existingArticle.setIsSlideShow(articleRequest.getIsSlideShow());
        existingArticle.setCreatedAt(LocalDateTime.now());
        existingArticle.setUpdatedAt(LocalDateTime.now());

        return articlesRepository.save(existingArticle);
    }

    public void deleteArticle(Integer id){
        Article existingArticle = findById(id);
        articlesRepository.delete(existingArticle);
    }

    @Transactional
    public void connectArticleWithCategories(Integer articleId, Integer categoryId) {
        Article article = articlesRepository.findById(articleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article id is not found"));
        Categories category = categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categories id is not found"));

        // Cek apakah artikel sudah memiliki kategori ini
        if (!article.getCategories().contains(category)) {
            article.getCategories().add(category);
        }
    }

}
