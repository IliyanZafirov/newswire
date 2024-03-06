package com.newswire.newswire.controller;

import com.newswire.newswire.entity.Article;
import com.newswire.newswire.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/articles")
public class ArticleController {

    private static final Logger LOGGER = Logger.getLogger(ArticleController.class.getName());
    private final ArticleService articleService;
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAll() {
        List<Article> articles = articleService.findAll();
        if (!articles.isEmpty()) {
            return ResponseEntity.ok(articles);
        } else {
            return ResponseEntity.notFound().build();
        }
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (articleService.existsById(id)) {
            articleService.deleteById(id);
            LOGGER.info("Article deleted successfully with ID: " + id);
            return ResponseEntity.noContent().build();
        }
        LOGGER.severe("Article was not found");
        return ResponseEntity.notFound().build();
    }
}
