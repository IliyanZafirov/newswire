package com.newswire.newswire.service;

import com.newswire.newswire.entity.Article;
import com.newswire.newswire.exception.InvalidArticleException;
import com.newswire.newswire.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void create(Article article) {
        try {
            validateArticle(article);
            articleRepository.save(article);
        } catch (IllegalArgumentException exception) {
            throw new InvalidArticleException(exception.getMessage());
        }
    }

    private void validateArticle(Article article) {
        if (article == null) {
            throw new IllegalArgumentException("Article cannot be null");
        }
        if (article.getTitle() == null) {
            throw new IllegalArgumentException("Article title cannot be null");
        }
        if (article.getTitle().isBlank()) {
            throw new IllegalArgumentException("Article title cannot be blank");
        }
        if (article.getContent() == null) {
            throw new IllegalArgumentException("Article content cannot be null");
        }
        if (article.getContent().isBlank()) {
            throw new IllegalArgumentException("Article content cannot be blank");
        }
        if (article.getCategory() == null) {
            throw new IllegalArgumentException("Article category cannot be null");
        }
        if (article.getImageURL() == null) {
            throw new IllegalArgumentException("Article image URL cannot be null");
        }
        if (article.getImageURL().isBlank()) {
            throw new IllegalArgumentException("Article image URL cannot be blank");
        }
    }
}
