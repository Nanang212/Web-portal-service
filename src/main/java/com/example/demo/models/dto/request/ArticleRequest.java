package com.example.demo.models.dto.request;

import com.example.demo.models.ArticleStatus;
import com.example.demo.models.ArticleType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleRequest {
    private String title;
    private String slug;
    private String body;
    private String banner;
    private String image;
    private Integer counter;
    private ArticleType type;
    private ArticleStatus status;
    private Boolean isSlideShow;
}
