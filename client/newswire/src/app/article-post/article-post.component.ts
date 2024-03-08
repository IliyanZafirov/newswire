import { Component } from '@angular/core';
import { ArticleService } from '../article-service/article.service';
import { Router } from '@angular/router';
import { Article } from '../model/article.model';

@Component({
  selector: 'app-article-post',
  standalone: true,
  imports: [],
  templateUrl: './article-post.component.html',
  styleUrl: './article-post.component.css'
})
export class ArticlePostComponent {
  article: Article=<Article>{};

  constructor(private articleService: ArticleService, private router: Router) {}

  onSubmit() {
    this.articleService.post(this.article).subscribe({
      next: () => {
        this.router.navigate(["/"]);
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
}
