package com.newswire.newswire.controller;

import com.newswire.newswire.entity.Article;
import com.newswire.newswire.entity.Category;
import com.newswire.newswire.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@CrossOrigin
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
            LOGGER.info("Returned all articles");
            return ResponseEntity.ok(articles);
        } else {
            LOGGER.severe("No articles found");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getById(@PathVariable Long id) {
        Optional<Article> article = articleService.findById(id);
        if (article.isPresent()) {
            LOGGER.info("Article was found with ID: " + id);
            return ResponseEntity.ok(article.get());
        } else {
            LOGGER.info("Article was not found with ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Article>> getByCategory(@PathVariable String category) {
        try {
            Category stringAsCategoryObj = Category.valueOf(category.toUpperCase());
            List<Article> articles = articleService.findAllByCategory(stringAsCategoryObj);
            if (!articles.isEmpty()) {
                LOGGER.info("Articles found in category: " + stringAsCategoryObj);
                return ResponseEntity.ok(articles);
            } else {
                LOGGER.info("No articles found in category: " + stringAsCategoryObj);
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            LOGGER.severe("Invalid category: " + category);
            return ResponseEntity.badRequest().build();
        }
    }

//    @GetMapping("/category/{category}")
//    public ResponseEntity<List<Article>> getByCategory(@PathVariable Category category) {
//        Optional<List<Article>> articles = articleService.findByCategory(category);
//        return articles.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @PostMapping
    public ResponseEntity<Article> post(@RequestBody Article article, UriComponentsBuilder ucb) {
        try {
            Article savedArticle = articleService.save(article);
            LOGGER.info("Article created successfully with ID: " + savedArticle.getId());
            URI location = ucb.path("/api/v1/articles/{id}").buildAndExpand(savedArticle.getId()).toUri();
            return ResponseEntity.created(location).body(savedArticle);
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

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable Long id, @RequestBody Article articleUpdate) {
        Article article = articleService.findById(id).orElse(null);
        if (article != null) {
            Article updatedArticle = new Article(id, articleUpdate.getTitle(), articleUpdate.getContent(),
                    articleUpdate.getCategory(), articleUpdate.getImageURL(), articleUpdate.getPublicationDate());
            articleService.save(updatedArticle);
            LOGGER.info("Article updated successfully with ID: " + id);
            return ResponseEntity.noContent().build();
        }
        LOGGER.severe("Article was not found with ID: " + id);
        return ResponseEntity.notFound().build();
    }
}
