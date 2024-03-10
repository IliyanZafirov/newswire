import { Component, OnDestroy, OnInit } from '@angular/core';
import { Article } from '../model/article.model';
import { ArticleService } from '../article-service/article.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgOptimizedImage } from '@angular/common';
import { Subscription } from 'rxjs';
import { FormControl, FormGroup, ReactiveFormsModule, UntypedFormControl, UntypedFormGroup } from '@angular/forms';

@Component({
  selector: 'app-article-details',
  standalone: true,
  imports: [NgOptimizedImage, ReactiveFormsModule],
  templateUrl: './article-details.component.html',
  styleUrl: './article-details.component.css'
})

export class ArticleDetailsComponent implements OnInit, OnDestroy{
  article: Article=<Article>{};
  subscriptions: Subscription[]=[];
  id: number = 0;
  
  articleForm = new UntypedFormGroup ({
    title: new UntypedFormControl('',  {nonNullable: true}),
    content: new UntypedFormControl('',  {nonNullable: true}),
    category: new UntypedFormControl('',  {nonNullable: true}),
    publicationDate: new UntypedFormControl('',  {nonNullable: true}),
    imageURL: new UntypedFormControl('',  {nonNullable: true})
  });

  constructor(private articleService: ArticleService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.subscriptions.push(
      this.route.params.subscribe(params => {this.id = params['id']})
    );
    this.subscriptions.push(
      this.articleService.get(this.id)
        .subscribe({
          next: (data) => {
            this.articleForm.get('title')?.setValue(data.title);
            this.articleForm.get('content')?.setValue(data.content);
            this.articleForm.get('category')?.setValue(data.category);
            this.articleForm.get('publicationDate')?.setValue(data.publicationDate);
            this.articleForm.get('imageURL')?.setValue(data.imageURL);
            console.log(this.articleForm.get('title')?.value);
          },
          error: (e) => console.error(e)
        })
    );
  }    

  ngOnDestroy(): void {
    this.subscriptions.forEach(s =>  s.unsubscribe());      
  }

  onSubmit() {
    this.article = this.articleForm.value as Article;
    this.articleService.update(this.id, this.article)
    .subscribe({
      next: () => {
        this.router.navigate(["/"]);
      },
      error: (error) => {
        console.error(error);
      }
    });
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
