package com.newswire.newswire.repository;

import com.newswire.newswire.entity.Article;
import com.newswire.newswire.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByCategory(Category category);
}
