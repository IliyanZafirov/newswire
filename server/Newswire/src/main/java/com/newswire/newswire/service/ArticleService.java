package com.newswire.newswire.service;

import com.newswire.newswire.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Article save(Article article);
    void deleteById(Long id);
    boolean existsById(Long id);
    List<Article> findAll();
    Optional<Article> findById(Long id);
}
