import { Component, inject } from '@angular/core';
import { Article } from '../model/article.model';
import { ArticleService } from '../article-service/article.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgOptimizedImage } from '@angular/common';

@Component({
  selector: 'app-article-details',
  standalone: true,
  imports: [NgOptimizedImage],
  templateUrl: './article-details.component.html',
  styleUrl: './article-details.component.css'
})

export class ArticleDetailsComponent {
  article: Article=<Article>{};
  
  constructor(private articleService: ArticleService, private route: ActivatedRoute, private router: Router) {
    this.article = this.getArticle(this.route.snapshot.params['id']);
  }

  getArticle(id: number): any {
    this.articleService.get(id)
      .subscribe({
        next: (data) => {
          this.article = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  deleteArticle(): void {
    this.articleService.delete(this.route.snapshot.params['id']).subscribe({
      next: () => {
        this.router.navigate(["/"]);
      },
      error: (error) => {
        console.error(error);
      }
    });
  } 
}
