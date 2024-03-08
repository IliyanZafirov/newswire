import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { Article } from '../model/article.model';
import { ArticleService } from '../article-service/article.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgOptimizedImage } from '@angular/common';
import { Subscription, combineLatest } from 'rxjs';

@Component({
  selector: 'app-article-details',
  standalone: true,
  imports: [NgOptimizedImage],
  templateUrl: './article-details.component.html',
  styleUrl: './article-details.component.css'
})

export class ArticleDetailsComponent implements OnInit, OnDestroy{
  article: Article=<Article>{};
  subscriptions: Subscription[]=[];
  id: number = 0;
  constructor(private articleService: ArticleService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.subscriptions.push(
      this.route.params.subscribe(params => {this.id = params['id']})
    );
    this.subscriptions.push(
      this.articleService.get(this.id)
        .subscribe({
          next: (data) => {
            this.article = data;
            console.log(data);
          },
          error: (e) => console.error(e)
        })
    );
  }    

  ngOnDestroy(): void {
    this.subscriptions.forEach(s =>  s.unsubscribe());      
  }

  deleteArticle(): void {
    this.subscriptions.push(
      this.articleService.delete(this.route.snapshot.params['id'])
      .subscribe({
        next: () => {
          this.router.navigate(["/"]);
        },
        error: (error) =>  console.error(error)
      })
    );
  }
}
