package com.newswire.newswire.service;

import com.newswire.newswire.entity.Article;

public interface ArticleService {
    public Article create(Article article);
    void deleteById(Long id);
    boolean existsById(Long id);
}
