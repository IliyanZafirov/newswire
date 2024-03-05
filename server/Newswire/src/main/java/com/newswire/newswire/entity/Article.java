package com.newswire.newswire.entity;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Entity
public class Article {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    @Column(length = 3000)
    @NonNull
    private String content;

    @Enumerated(EnumType.STRING)
    private Category category;

    @NonNull
    private String imageURL;

    @NonNull
    private LocalDateTime publicationDate;

    public Article(Long id, String title, String content, Category category, String imageURL, LocalDateTime publicationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.imageURL = imageURL;
        this.publicationDate = publicationDate;
    }

    public Article() {}

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
