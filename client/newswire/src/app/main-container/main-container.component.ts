import { Component, OnDestroy, OnInit } from '@angular/core';
import { Article } from '../model/article.model';
import { ArticleService } from '../article-service/article.service';
import { ArticleCardComponent } from '../article-card/article-card.component';
import { NgOptimizedImage } from '@angular/common';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-main-container',
  standalone: true,
  imports: [ArticleCardComponent, NgOptimizedImage],
  templateUrl: './main-container.component.html',
  styleUrl: './main-container.component.css'
})
export class MainContainerComponent implements OnInit, OnDestroy {
  articles?: Article[];
  subscriptions: Subscription[]=[];
  constructor(private articleService: ArticleService) {}

  ngOnInit(): void {
    this.subscriptions.push(
      this.articleService.getAll()
      .subscribe({
        next: (data) => {
          this.articles = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      })
    );
  }

  ngOnDestroy(): void {
      this.subscriptions.forEach(s => s.unsubscribe());
  }
}
