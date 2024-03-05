package com.newswire.newswire.controller;

import com.newswire.newswire.entity.Article;
import com.newswire.newswire.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/articles")
public class ArticleController {

    private static final Logger LOGGER = Logger.getLogger(ArticleController.class.getName());
    private final ArticleService articleService;
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<Article> post(@RequestBody Article article) {
        try {
            Article savedArticle = articleService.create(article);
            LOGGER.info("Article created successfully with ID: " + savedArticle.getId());
            return ResponseEntity.ok(savedArticle);
        } catch (Exception e) {
            LOGGER.severe("Error occurred while creating article: " + e.getMessage());
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
